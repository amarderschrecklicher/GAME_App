package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.Game
import ba.etf.rma23.projekat.HomeFragment
import ba.etf.rma23.projekat.SavedGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GamesRepository {


     suspend fun getGamesByName(name:String):List<Game>{
         return withContext(Dispatchers.IO) {
             val response = IGDBApiConfig.ApiAdapter.retrofit.getGames(IGDBApiConfig.FIELDS + "search \"$name\";limit 10;")
             val real = convert(response.body()!!)
             HomeFragment.gamesList = real
             return@withContext real!!
         }
    }

     suspend fun getGamesSafe(name:String):List<Game>{
         if(AccountApiConfig.userAge == 0)return emptyList()
        val response = IGDBApiConfig.ApiAdapter.retrofit.getGames(IGDBApiConfig.SAFE_FIELDS + "search \"$name\";limit 10;")
         val real = convert(response.body()!!)
         HomeFragment.gamesList = real
        return real!!
    }

     fun sortGames():List<Game>{
            return  HomeFragment.gamesList!!.filter { it.favorite } + HomeFragment.gamesList!!.filter { !it.favorite}
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