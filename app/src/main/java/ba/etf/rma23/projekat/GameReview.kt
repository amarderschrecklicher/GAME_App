package ba.etf.rma23.projekat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GameReview(
    @PrimaryKey @SerializedName("Gameid") val gameId: Int?,
    @ColumnInfo("review") @SerializedName("review") val review: String?,
    @ColumnInfo("rating") @SerializedName("rating") val rating: Int?,
    @ColumnInfo("online") var online : Boolean = false,
    @SerializedName("timestamp") val timestamp: Long?
)
