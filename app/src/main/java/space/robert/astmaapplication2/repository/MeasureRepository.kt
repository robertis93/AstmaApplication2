package space.robert.astmaapplication2.repository

import androidx.lifecycle.LiveData
import space.robert.astmaapplication2.data.MeasureDao
import space.robert.astmaapplication2.model.Measure

class MeasureRepository(private val measureDao: MeasureDao) {

    val readAllData: LiveData<List<Measure>> = measureDao.readAll()
    suspend fun addMeasure(measure: Measure) {
        measureDao.addMeasure(measure)
    }

    suspend fun updateMeasure(measure: Measure) {
        measureDao.updateMeasure(measure)
    }

    suspend fun deleteMeasure(measure: Measure) {
        measureDao.deleteMeasures(measure)
    }

    suspend fun deleteAllMuasures() {
        measureDao.deleteAllMeasures()
    }

}