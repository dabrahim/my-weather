package tech.keyops.challenge.myweather.arch.data.repository

import tech.keyops.challenge.myweather.arch.data.datasource.CityDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityRepository @Inject constructor(private val cityDataSource: CityDataSource) {

    fun getCities() =
        cityDataSource.getCities()
}