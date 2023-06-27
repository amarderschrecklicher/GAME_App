package ba.etf.rma23.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import ba.etf.rma23.projekat.*
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

    @SuppressLint("SuspiciousIndentation")
    suspend fun sendOfflineReviews(context: Context):Int{
        return withContext(Dispatchers.IO) {
            var sent = 0
            try {
                val reviews = getOfflineReviews(context)

                for(review in reviews) {
                   ReviewsApiConfig.ApiAdapter.retrofit.addReview(review.gameId!!,AccountApiConfig.accountHash,review).isSuccessful
                        sent += 1
                        val db = Database.getInstance(context)
                        db.reviewDAO().update(true, review.id!!)
                }
                return@withContext sent
            } catch (error: Exception) {
                return@withContext sent
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun sendReview( context : Context, review : GameReview):Boolean{

            return withContext(Dispatchers.IO) {
                try {
                var response = false
                for (games in AccountApiConfig.favoriteGames!!) {
                    if (games.id == review.gameId) {
                        response = true
                        break
                    }
                }
                if (!response) {
                    val gamesFav = IGDBApiConfig.ApiAdapter.retrofit.getGames(
                        "where id =  ${review.gameId};"
                    )
                    for (game in gamesFav.body()!!) {
                        if (game.ageRatings != null && game.ageRatings.isNotEmpty()) {
                            val firstAgeRating = game.ageRatings[0]
                            if (firstAgeRating.category == 2 || firstAgeRating.category == 1) {
                                game.esrbRating = firstAgeRating.rating.toString()
                            }
                        }
                        game.platforms?.let { platforms ->
                            if (platforms.isNotEmpty()) {
                                game.platform = ""
                                for (plat in platforms)
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
                 ReviewsApiConfig.ApiAdapter.retrofit.addReview(
                    review.gameId!!,
                    AccountApiConfig.accountHash,
                    review
                )

                return@withContext true

        }
        catch (error: Exception){
            val db = Database.getInstance(context)
            db!!.reviewDAO().insertAll(review)
            return@withContext false
        }
            }
    }

    suspend fun getReviewsForGame(igdb_id :Int):List<GameReview>{
        return withContext(Dispatchers.IO) {
            val response = ReviewsApiConfig.ApiAdapter.retrofit.getReview(igdb_id).body()
            if(response?.isEmpty() == true)
                return@withContext emptyList()
            else  return@withContext response!!
        }
    }
}