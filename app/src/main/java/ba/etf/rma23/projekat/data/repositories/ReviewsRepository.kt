package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.GameReview

object ReviewsRepository {

    fun getOfflineReviews():List <GameReview>{
        return emptyList()
    }

    fun sendOfflineReviews():Int{
        return 0
    }

    fun sendReview( review : GameReview):Boolean{
        return true
    }

    fun getReviewsForGame(igdb_id :Int):List<GameReview>{
        return emptyList()
    }
}