package tech.keyops.challenge.myweather.arch.data.datasource.impl.remote.response

// Thanks to Gson, all we need is a class matching with the API response structure
// to have the parsing handled for us.
class GetCityWeatherDataResponse(val weather: List<Weather>, val main: Main, val name: String) {

    inner class Main(val temp: String)

    inner class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
    )
}