package tech.keyops.challenge.myweather.arch.data.datasource

import io.reactivex.Observable
import tech.keyops.challenge.myweather.arch.domain.Weather

interface WeatherDataSource {

    fun getCityWeatherData(cityName: String): Observable<Weather>
}