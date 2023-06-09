package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

object AccountGamesRepository {

    fun setHash(acHash:String):Boolean{
        AccountApiConfig.accountHash = acHash
        return true
    }

    fun getHash():String{
        return AccountApiConfig.accountHash
    }

    suspend fun getSavedGames():List<Game> {
        return withContext(Dispatchers.IO) {
            val games = AccountApiConfig.ApiAdapter.retrofit.getGames(AccountApiConfig.accountHash)
            var query = "("
            for (game in games.body()!!) {
                query += game.igdbID.toString() + ","
            }
            var gamesFav : Response<List<SavedGame>>? = null

            if(AccountApiConfig.userAge >= 18)
               gamesFav = IGDBApiConfig.ApiAdapter.retrofit.getGames( IGDBApiConfig.FIELDS +"where id =" + query.dropLast(1) + ");")
           else if(AccountApiConfig.userAge > 0)
               gamesFav = IGDBApiConfig.ApiAdapter.retrofit.getGames( IGDBApiConfig.SAFE_FIELDS +"where id =" + query.dropLast(1) + ");")

            if(gamesFav!!.isSuccessful) {

                val realFav = convert(gamesFav!!.body()!!)

                for (game in AccountApiConfig.favoriteGames!!) game.favorite
                for (game in realFav!!) game.favorite
                return@withContext realFav
            }
            return@withContext emptyList()
        }
    }

    suspend fun saveGame(game: Game){
        game.favorite = true
        AccountApiConfig.ApiAdapter.retrofit.addGame(FavoriteGame(AddGame(game.id,game.name)),AccountApiConfig.accountHash)
        AccountApiConfig.favoriteGames!!.add(game)
    }

    suspend fun removeGame(id:Int):Boolean{
        AccountApiConfig.favoriteGames!!.removeIf { it.id == id }
        AccountApiConfig.ApiAdapter.retrofit.deleteGame(id,AccountApiConfig.accountHash)
       return true
    }

    suspend fun removeNonSafe():Boolean{
        for(game in AccountApiConfig.favoriteGames!!){
           if(game.esrbRating!!.split(" ")[0] == "1" && game.esrbRating.split(" ")[1] == "12" ||
               game.esrbRating!!.split(" ")[0] == "2" && game.esrbRating.split(" ")[1] == "5")
                    AccountApiConfig.ApiAdapter.retrofit.deleteGame(game.id,AccountApiConfig.accountHash)
                     AccountApiConfig.favoriteGames!!.removeIf { it.id == game.id }
                }
        return true
    }

    fun getGamesContainingString(query:String):List<Game>{
        return AccountApiConfig.favoriteGames!!.filter { it.name.contains(query) }
    }

    fun setAge(age:Int):Boolean{
        AccountApiConfig.userAge = age
        if(age in 1..17)
            return false
        return true
    }

    suspend fun convert(list: List<SavedGame>?):ArrayList<Game>?{
        return withContext(Dispatchers.IO) {
            var realFav : ArrayList<Game>? = ArrayList()

            for(game in list!!){

                val a = game.esrbRating!![0]

                realFav!!.add(Game(game.id,
                    game.name!!, game.platform!![0].name, game.releaseDate!![0].human,game.rating,
                    game.attributesImage!!.url, a.category.toString() + " " + a.rating,
                    game.developer!![0].company!!.name, game.genre!![0].name,game.description,"",
                    null,true)
                )

            }
            return@withContext realFav!!
        }}
}