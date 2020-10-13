package com.dartharrmi.weathery.di

import android.content.Context
import androidx.room.Room
import com.dartharrmi.weathery.repositories.OpenWeatherMapLocalDataSource
import com.dartharrmi.weathery.repositories.OpenWeatherMapRemoteDataSource
import com.dartharrmi.weathery.repositories.WeatherRepository
import com.dartharrmi.weathery.repositories.room.AppDatabase
import com.dartharrmi.weathery.usecases.DownloadIconUseCase
import com.dartharrmi.weathery.usecases.FindCitiesByLocationUseCase
import com.dartharrmi.weathery.usecases.FindCitiesByNameUseCase
import com.dartharrmi.weathery.webservice.utils.PublicOkHttpClient

/**
 * Container of objects shared across the whole app
 */
class DependencyContainer(private val context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "weathery"
    ).build()

    private val localDataSource = OpenWeatherMapLocalDataSource(db)
    private val remoteDataSource = OpenWeatherMapRemoteDataSource(context, PublicOkHttpClient())

    val repository = WeatherRepository(localDataSource, remoteDataSource)

    val findCitiesByNameUseCase = FindCitiesByNameUseCase(repository)

    val findCitiesByLocationUseCase = FindCitiesByLocationUseCase(repository)

    val downloadIconUseCase = DownloadIconUseCase(repository)
}