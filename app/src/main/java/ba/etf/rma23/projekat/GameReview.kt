package ba.etf.rma23.projekat

import com.google.gson.annotations.SerializedName

data class GameReview(
    @SerializedName("Gameid") val gameId: Int?,
    @SerializedName("review") val review: String?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("timestamp") val timestamp: Long?
)
