package com.joerakhimov.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joerakhimov.weather.model.ForecastResponse
import com.joerakhimov.weather.model.LocationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val _location = MutableLiveData<LocationItem>()
    val location: LiveData<LocationItem> = _location

    private val _forecastResponse = MutableLiveData<ForecastResponse>()
    val forecastResponse: LiveData<ForecastResponse> = _forecastResponse

    init {
        getLocation()
    }

    private fun getLocation() {
        viewModelScope.launch {
            val location = api.getLocation()
            _location.value = location
            location.url?.let { getForecast(it) }
        }
    }

    private fun getForecast(url: String) {
        viewModelScope.launch {
            _forecastResponse.value = api.getForecast(url)
        }
    }

}