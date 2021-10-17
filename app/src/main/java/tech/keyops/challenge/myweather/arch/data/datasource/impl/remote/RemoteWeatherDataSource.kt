package tech.keyops.challenge.myweather.arch.data.datasource.impl.remote

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import tech.keyops.challenge.myweather.arch.data.datasource.WeatherDataSource
import tech.keyops.challenge.myweather.arch.domain.Weather
import javax.inject.Inject

class RemoteWeatherDataSource @Inject constructor(private val weatherWebService: WeatherWebService) :
    WeatherDataSource {

    override fun getCityWeatherData(cityName: String): Observable<Weather> {
        return weatherWebService.fetchCityWeather(cityName)
            .map { response ->
                Weather(response.weather[0].icon, response.main.temp, response.name)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}