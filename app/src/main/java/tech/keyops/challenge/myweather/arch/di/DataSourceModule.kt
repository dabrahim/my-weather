package tech.keyops.challenge.myweather.arch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tech.keyops.challenge.myweather.arch.data.datasource.CityDataSource
import tech.keyops.challenge.myweather.arch.data.datasource.WeatherDataSource
import tech.keyops.challenge.myweather.arch.data.datasource.impl.local.LocalCityDataSource
import tech.keyops.challenge.myweather.arch.data.datasource.impl.remote.RemoteWeatherDataSource

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Binds
    abstract fun provideCityDataSource(localCityDataSource: LocalCityDataSource):
            CityDataSource

    @Binds
    abstract fun provideWeatherDataSource(remoteWeatherDataSource: RemoteWeatherDataSource):
            WeatherDataSource
}