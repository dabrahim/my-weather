package tech.keyops.challenge.myweather.arc.data.source.remote

object Config {
    const val API_URL = "https://api.openweathermap.org/data/2.5/"
    const val API_APP_ID = "070753fb14f4b2516949a25caf1c4885"
    const val API_UNITS = "metric"

    fun getIconPath(iconId: String): String {
        return "https://openweathermap.org/img/w/${iconId}.png"
    }
}