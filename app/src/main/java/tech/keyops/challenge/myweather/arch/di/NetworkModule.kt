package tech.keyops.challenge.myweather.arch.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tech.keyops.challenge.myweather.BuildConfig
import tech.keyops.challenge.myweather.arch.data.datasource.impl.remote.Config
import tech.keyops.challenge.myweather.arch.data.datasource.impl.remote.WeatherWebService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
            )
        }
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                var request = chain.request()

                val url = request.url
                    .newBuilder()
                    .addQueryParameter("appid", Config.API_APP_ID)
                    .addQueryParameter("units", Config.API_UNITS)
                    .build()

                request = request.newBuilder()
                    .url(url)
                    .build()

                chain.proceed(request)
            })
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Config.API_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherWebService(retrofit: Retrofit): WeatherWebService {
        return retrofit.create(WeatherWebService::class.java)
    }
}
