package com.joerakhimov.weather

import com.joerakhimov.weather.forecast.ForecastResponse
import com.joerakhimov.weather.forecast.LocationItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/weather/location")
    suspend fun getLocation(): LocationItem

    @GET("/weather/autocomplete")
    suspend fun autocomplete(@Query("q") query: String): List<LocationItem>

    @GET("/weather/forecast")
    suspend fun getForecast(@Query("url") url: String): ForecastResponse

}