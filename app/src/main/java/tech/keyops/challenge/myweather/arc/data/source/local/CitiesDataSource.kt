package tech.keyops.challenge.myweather.arc.data.source.local

import tech.keyops.challenge.myweather.arc.data.source.City
import javax.inject.Inject

class CitiesDataSource @Inject constructor() {

    fun getCities(): List<City> {
        return listOf(
            City("Abidjan"),
            City("Lyon"),
            City("Londres")
        )
    }
}