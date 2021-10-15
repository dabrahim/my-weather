package tech.keyops.challenge.myweather.arc.data.source.remote.response

class GetCityWeatherDataResponse(val weather: List<Weather>, val main: Main, val name: String) {

    inner class Main(val temp: String)

    inner class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
    )
}