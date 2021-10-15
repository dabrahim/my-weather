package tech.keyops.challenge.myweather.arc.data

import tech.keyops.challenge.myweather.arc.data.source.City
import tech.keyops.challenge.myweather.arc.data.source.local.CitiesDataSource
import javax.inject.Inject

class CitiesRepository @Inject constructor(private val citiesDataSource: CitiesDataSource) {

    fun getCities(): List<City> {
        return citiesDataSource.getCities()
    }
}