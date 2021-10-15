package tech.keyops.challenge.myweather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.keyops.challenge.myweather.arc.data.CitiesRepository
import tech.keyops.challenge.myweather.arc.data.source.City
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(citiesRepository: CitiesRepository) :
    ViewModel() {

    private val _cities: MutableLiveData<List<City>> = MutableLiveData(citiesRepository.getCities())

    val cities: LiveData<List<City>>
        get() = _cities
}
