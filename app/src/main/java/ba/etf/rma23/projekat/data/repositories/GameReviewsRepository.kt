package ba.etf.rma23.projekat.data.repositories

import android.content.Context
import ba.etf.rma23.projekat.GameReview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
object GameReviewsRepository {

    suspend fun getOfflineReviews(context : Context):List <GameReview>{
        return withContext(Dispatchers.IO) {
            try {
                val db = Database.getInstance(context)
                val reviews = db!!.reviewDAO().getAll()
                return@withContext reviews.filter { !it.online }
            } catch (error: Exception) {
                return@withContext emptyList()
            }
        }
    }

    suspend fun sendOfflineReviews(context: Context,review : GameReview):Int{
        return withContext(Dispatchers.IO) {
            try {
                val db = Database.getInstance(context)
                val reviews = db!!.reviewDAO().getAll().filter { !it.online }
                var sent = 0
                for(review in reviews) {
                    if(ReviewsApiConfig.ApiAdapter.retrofit.addReview(review.gameId!!,AccountApiConfig.accountHash,review).isSuccessful)
                        sent += 1
                }
                return@withContext sent
            } catch (error: Exception) {
                return@withContext 5
            }
        }
    }

    suspend fun sendReview(review : GameReview,context : Context):Boolean{
        return withContext(Dispatchers.IO) {
            var response = false
            for(games in AccountApiConfig.favoriteGames!!){
                if(games.id == review.gameId){
                    response = true
                    break
                }
            }
            if(!response){
                val gamesFav = IGDBApiConfig.ApiAdapter.retrofit.getGames(
                    "where id =  ${review.gameId};")
                for (game in gamesFav.body()!!) {
                    if (game.ageRatings != null && game.ageRatings.isNotEmpty()) {
                        val firstAgeRating = game.ageRatings[0]
                        if (firstAgeRating.category == 2 || firstAgeRating.category == 1) {
                            game.esrbRating = firstAgeRating.rating.toString()
                        }
                    }
                    game.platforms?.let{platforms->
                        if(platforms.isNotEmpty()){
                            game.platform=""
                            for(plat in platforms)
                                game.platform += plat.name + ", "
                        }
                    }
                    game.platform = game.platform?.dropLast(2)
                    game.developer = game.companies?.get(0)?.company?.name
                    game.releaseDate = game.releaseDates?.get(0)?.human
                    game.genre = game.genres?.get(0)?.name
                    game.attributesImage = game.imageUrl?.url
            }
            AccountGamesRepository.saveGame(gamesFav.body()!![0])
        }
          response =  ReviewsApiConfig.ApiAdapter.retrofit.addReview(review.gameId!!,AccountApiConfig.accountHash,review).isSuccessful

            if(!response){
                try {
                    val db = Database.getInstance(context)
                    db!!.reviewDAO().insertAll(review)
                } catch (error: Exception) {
                    return@withContext false
                }
            }
            return@withContext response
        }
    }

    suspend fun getReviewsForGame(igdb_id :Int):List<GameReview>{
        return withContext(Dispatchers.IO) {
            val response = ReviewsApiConfig.ApiAdapter.retrofit.getReview(igdb_id,AccountApiConfig.accountHash).body()
            if(response?.isEmpty() == true)return@withContext emptyList()
            else  return@withContext response!!
        }
    }
}