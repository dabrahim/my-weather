package tech.keyops.challenge.myweather.arch.usecase

import tech.keyops.challenge.myweather.arch.data.repository.WeatherRepository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    fun invoke(cityName: String) =
        weatherRepository.getCityWeatherData(cityName)
}