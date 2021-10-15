package tech.keyops.challenge.myweather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.keyops.challenge.myweather.arc.data.CitiesRepository
import tech.keyops.challenge.myweather.arc.data.source.City
import tech.keyops.challenge.myweather.ui.utility.Event
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(citiesRepository: CitiesRepository) :
    ViewModel() {

    private val _cities: MutableLiveData<List<City>> = MutableLiveData(citiesRepository.getCities())

    val cities: LiveData<List<City>>
        get() = _cities

    private val _navigateToWeatherScreenEvent = MutableLiveData<Event<String>>()

    val navigateToWeatherScreenEvent: LiveData<Event<String>>
        get() = _navigateToWeatherScreenEvent

    private fun dispatchNavigateToWeatherScreenEvent(city: String) {
        _navigateToWeatherScreenEvent.value = Event(city)
    }

    fun onCityItemClick(index: Int) {
        val city = _cities.value?.get(index)

        city?.let {
            dispatchNavigateToWeatherScreenEvent(city.name)
        }
    }
}
