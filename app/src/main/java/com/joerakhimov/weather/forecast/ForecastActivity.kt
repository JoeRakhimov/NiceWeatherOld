package com.joerakhimov.weather.forecast

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.joerakhimov.weather.R
import com.joerakhimov.weather.location.LocationActivity
import com.joerakhimov.weather.location.LocationViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {

    private val locationViewModel: LocationViewModel by viewModels()
    private val forecastViewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            initObservers()
            locationViewModel.getLocation()
        }
    }

    private fun initObservers() {

        locationViewModel.location.observe(this) { location ->
            title = location.name
            location.url?.let { forecastViewModel.getForecast(it) }
        }

        forecastViewModel.forecastResponse.observe(this) {
            text_current_temp.text = "${it.current?.tempC}°"
            text_current_condition.text = it.current?.condition?.text
            it.forecast?.forecastday?.let { forecast ->
                recycler_forecast.layoutManager = LinearLayoutManager(this)
                recycler_forecast.adapter = ForecastAdapter(forecast)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.forecast_menu, menu)
        return true
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val location = result.data?.getSerializableExtra("LOCATION")
            locationViewModel.setLocation(location as LocationItem?)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_location -> {
                startForResult.launch(Intent(this, LocationActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}