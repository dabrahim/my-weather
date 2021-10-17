package tech.keyops.challenge.myweather.arch.data.datasource.impl.local

import tech.keyops.challenge.myweather.arch.data.datasource.CityDataSource
import tech.keyops.challenge.myweather.arch.domain.City
import javax.inject.Inject

class LocalCityDataSource @Inject constructor() : CityDataSource {

    override fun getCities(): List<City> {
        return listOf(
            City("Abidjan"),
            City("Lyon"),
            City("Londres")
        )
    }
}