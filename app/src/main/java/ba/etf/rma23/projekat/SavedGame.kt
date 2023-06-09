package ba.etf.rma23.projekat

import com.google.gson.annotations.SerializedName

data class SavedGame(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("platforms") val platform: List<Attributes>?,
    @SerializedName("release_date") val releaseDate: List<Attributes>?,
    @SerializedName("rating") val rating: Double?,
    @SerializedName("cover") val attributesImage: Attributes?,
    @SerializedName("age_ratings") val esrbRating: List<Attributes>?,
    @SerializedName("involved_companies") val developer: List<Company>?,
    @SerializedName("genres") val genre: List<Attributes>?,
    @SerializedName("summary") val description: String?,
)
