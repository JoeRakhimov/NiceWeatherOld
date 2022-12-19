package com.joerakhimov.weather.forecast

import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joerakhimov.weather.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val _forecastResponse = MutableLiveData<ForecastResponse>()
    val forecastResponse: LiveData<ForecastResponse> = _forecastResponse

    fun getForecast(url: String) {
        viewModelScope.launch {
            _forecastResponse.value = api.getForecast(url)
        }
    }

}