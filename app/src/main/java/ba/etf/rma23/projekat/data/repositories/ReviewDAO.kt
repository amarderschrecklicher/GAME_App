package ba.etf.rma23.projekat.data.repositories

import androidx.room.Dao
import androidx.room.Insert
import ba.etf.rma23.projekat.GameReview
import androidx.room.Query

@Dao
interface ReviewDAO {
    @Query("Select * from gamereview")
    suspend fun getAll():List<GameReview>
    @Insert
    suspend fun insertAll(vararg reviews : GameReview)

}