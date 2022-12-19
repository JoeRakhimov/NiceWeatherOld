package com.joerakhimov.weather.location

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joerakhimov.weather.ApiService
import com.joerakhimov.weather.forecast.LocationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val api: ApiService): ViewModel() {

    private val _location = MutableLiveData<LocationItem>()
    val location: LiveData<LocationItem> = _location

    private val _searchQuery = MutableStateFlow("")

    fun getLocation() {
        viewModelScope.launch {
            val location = api.getLocation()
            _location.value = location
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setLocation(location: LocationItem?) {
        _location.value = location
    }

    val foundLocations: Flow<List<LocationItem>> = _searchQuery.debounce(1000).mapLatest { query->
        if (query.isEmpty()) {
            return@mapLatest emptyList<LocationItem>()
        } else {
            return@mapLatest api.autocomplete(query)
        }
    }

}