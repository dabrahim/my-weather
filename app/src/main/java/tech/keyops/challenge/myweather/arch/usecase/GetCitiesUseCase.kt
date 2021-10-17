package tech.keyops.challenge.myweather.arch.usecase

import tech.keyops.challenge.myweather.arch.data.repository.CityRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val cityRepository: CityRepository) {

    fun invoke() = cityRepository.getCities()
}