package tech.keyops.challenge.myweather.arc.data

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.keyops.challenge.myweather.arc.data.source.Weather
import tech.keyops.challenge.myweather.arc.data.source.remote.WeatherWebService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherWebService: WeatherWebService) {

    fun getCityWeather(city: String): Observable<Weather> {
        return weatherWebService.fetchCityWeather(city)
            .map { response ->
                Weather(response.weather[0].icon, response.main.temp, response.name)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}