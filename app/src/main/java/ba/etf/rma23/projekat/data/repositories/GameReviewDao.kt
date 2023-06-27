package ba.etf.rma23.projekat.data.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ba.etf.rma23.projekat.GameReview
import androidx.room.Query

@Dao
interface GameReviewDao {
    @Query("SELECT * FROM gamereview")
    suspend fun getAll():List<GameReview>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg reviews : GameReview)

}