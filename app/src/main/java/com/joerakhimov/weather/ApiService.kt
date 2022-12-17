package com.joerakhimov.weather

import com.joerakhimov.weather.model.ForecastResponse
import com.joerakhimov.weather.model.LocationItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/weather/location")
    suspend fun getLocation(): LocationItem

    @GET("/weather/forecast")
    suspend fun getForecast(@Query("url") url: String): ForecastResponse

}