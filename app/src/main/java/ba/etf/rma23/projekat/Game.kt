package ba.etf.rma23.projekat

import com.google.gson.annotations.SerializedName

data class Game(
     val id: Int,
     val name: String,
     val platform: String?,
     val releaseDate: String?,
     val rating: Double?,
     val attributesImage: String?,
     val esrbRating: String?,
     val developer: String?,
     val genre: String?,
     val description: String?,
     var fav: String?,
    val userImpressions: List<UserImpression>?,
    var favorite: Boolean = false
    )

