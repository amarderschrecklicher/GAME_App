package ba.etf.rma23.projekat.data.repositories

import ba.etf.rma23.projekat.AddGame
import ba.etf.rma23.projekat.FavoriteGame
import ba.etf.rma23.projekat.Game
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class ReviewsApiConfig {

    interface Api {

        @POST("account/{accountHash}/game/{gid}/gamereview")
        suspend fun addReview(@Path("gid",) gameId: Int, @Path("accountHash") accountHash: String): Response<Game>

        @GET("game/{gid}/gamereviews")
        suspend fun getReview(@Path("gid",) gameId: Int, @Path("accountHash") accountHash: String): Response<Game>

        @DELETE("account/{accountHash}/gamereviews")
        suspend fun deleteReview(@Path("accountHash") accountHash: String): Response<Boolean>

    }

    object ApiAdapter {
        val retrofit : Api = Retrofit.Builder()
            .baseUrl("https://rma23ws.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}

