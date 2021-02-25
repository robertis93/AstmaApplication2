package space.robert.astmaapplication2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import space.robert.astmaapplication2.model.Measure

@Dao
interface MeasureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun addMeasure(measure: Measure)

     @Update
     suspend fun updateMeasure(measure: Measure)

     @Query("SELECT * FROM measure_day ORDER BY id ASC")
     fun readAll() : LiveData<List<Measure>>

     @Delete
     suspend fun deleteMeasures(measure: Measure)

     @Query("DELETE FROM measure_day")
     suspend fun deleteAllMeasures()
}