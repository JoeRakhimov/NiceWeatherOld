package com.joerakhimov.weather.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(

	@field:SerializedName("current")
	val current: Current? = null,

	@field:SerializedName("location")
	val location: LocationItem? = null,

	@field:SerializedName("forecast")
	val forecast: Forecast? = null

)

data class LocationItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("region")
	val region: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class ForecastdayItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("condition")
	val condition: Condition? = null,

	@field:SerializedName("maxtemp_c")
	val maxtempC: Double? = null,

	@field:SerializedName("mintemp_c")
	val mintempC: Double? = null
)

data class Forecast(
	@field:SerializedName("forecastday")
	val forecastday: List<ForecastdayItem>? = null
)

data class Current(
	@field:SerializedName("condition")
	val condition: Condition? = null,

	@field:SerializedName("temp_c")
	val tempC: Double? = null
)

data class Condition(
	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("text")
	val text: String? = null
)
