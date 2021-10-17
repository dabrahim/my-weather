package tech.keyops.challenge.myweather.arch.data.repository

import tech.keyops.challenge.myweather.arch.data.datasource.WeatherDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherDataSource: WeatherDataSource) {

    fun getCityWeatherData(cityName: String) =
        weatherDataSource.getCityWeatherData(cityName)
}