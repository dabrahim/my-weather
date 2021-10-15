package tech.keyops.challenge.myweather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.keyops.challenge.myweather.arch.domain.City
import tech.keyops.challenge.myweather.arch.usecase.GetCitiesUseCase
import tech.keyops.challenge.myweather.ui.utility.Event
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(getCitiesUseCase: GetCitiesUseCase) :
    ViewModel() {

    private val _cities: MutableLiveData<List<City>> = MutableLiveData(getCitiesUseCase.invoke())

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
