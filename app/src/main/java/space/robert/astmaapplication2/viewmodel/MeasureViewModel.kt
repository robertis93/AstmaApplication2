package space.robert.astmaapplication2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.robert.astmaapplication2.data.MeasureDataBase
import space.robert.astmaapplication2.model.Measure
import space.robert.astmaapplication2.repository.MeasureRepository

class MeasureViewModel(application: Application): AndroidViewModel(application) {

     val readAllData: LiveData<List<Measure>>
    private val repository : MeasureRepository

    init {
        val measureDao = MeasureDataBase.getDataBase(application).measureDao()
        repository = MeasureRepository(measureDao)
        readAllData = repository.readAllData
    }

    fun addMeasure(measure: Measure) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMeasure(measure)
        }
    }

    fun updateMeasure(measure: Measure){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMeasure(measure)
        }
    }

    fun deleteMeasure(measure: Measure){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMeasure(measure)
        }
    }
    fun deleteAllMeasure(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMuasures()
        }
    }
}