package tech.keyops.challenge.myweather.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.keyops.challenge.myweather.arc.data.WeatherRepository
import tech.keyops.challenge.myweather.arc.data.source.Weather
import tech.keyops.challenge.myweather.ui.utility.CustomObserver
import tech.keyops.challenge.myweather.ui.utility.Event
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    savedState: SavedStateHandle
) : ViewModel() {

    private val cityName: String = savedState.get<String>("cityName") as String

    // ----
    private val _toggleLoaderVisibilityEvent = MutableLiveData<Event<Boolean>>()
    val toggleLoaderVisibilityEvent: LiveData<Event<Boolean>>
        get() = _toggleLoaderVisibilityEvent

    private fun dispatchToggleLoaderVisibilityEvent(show: Boolean) {
        _toggleLoaderVisibilityEvent.value = Event(show)
    }

    // ----
    private val _showErrorViewEvent = MutableLiveData<Event<Unit>>()
    val showErrorViewEvent: LiveData<Event<Unit>>
        get() = _showErrorViewEvent

    private fun dispatchShowErrorViewEvent() {
        _showErrorViewEvent.value = Event(Unit)
    }

    // ----
    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

    init {
        // Upon ViewModel creation, we fetch the data from the API
        fetchCityWeather()
    }

    private fun fetchCityWeather() {
        val observer = object : CustomObserver<Weather>() {
            override fun onSuccess(data: Weather) = onWeatherDataFetched(data)
            override fun onFailure(e: Throwable) = onFetchError(e)
        }

        weatherRepository.getCityWeather(cityName)
            .doOnSubscribe { dispatchToggleLoaderVisibilityEvent(true) } // We display the loader view while we get the data from the API
            .doAfterNext { dispatchToggleLoaderVisibilityEvent(false) } // When the data is loaded, we hide the loader view
            .subscribe(observer) // We register our observer
    }

    private fun onWeatherDataFetched(weather: Weather) {
        _weather.value = weather // We post a new value to notify all listeners
    }

    private fun onFetchError(e: Throwable) {
        // To simplify this application, we simply display a generic error view when
        // something bad happens
        dispatchShowErrorViewEvent()
    }
}