package tech.keyops.challenge.myweather.arch.data.datasource

import tech.keyops.challenge.myweather.arch.domain.City

interface CityDataSource {

    fun getCities(): List<City>
}