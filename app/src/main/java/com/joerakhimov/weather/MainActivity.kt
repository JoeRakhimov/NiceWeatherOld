package com.joerakhimov.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import androidx.activity.viewModels

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
    }

    private fun initObservers() {
        viewModel.forecastResponse.observe(this) {
            text_location.text = it.location?.name
            text_current_temp.text = "${it.current?.tempC}°"
            text_current_condition.text = it.current?.condition?.text
            it.forecast?.forecastday?.let { forecast ->
                recycler_forecast.layoutManager = LinearLayoutManager(this)
                recycler_forecast.adapter = ForecastAdapter(forecast)
            }
        }
    }

}