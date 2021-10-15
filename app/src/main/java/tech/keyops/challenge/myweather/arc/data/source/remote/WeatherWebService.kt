package tech.keyops.challenge.myweather.arc.data.source.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tech.keyops.challenge.myweather.arc.data.source.remote.response.GetCityWeatherDataResponse

interface WeatherWebService {

    @GET("weather/")
    fun fetchCityWeather(@Query("q") city: String): Observable<GetCityWeatherDataResponse>
}