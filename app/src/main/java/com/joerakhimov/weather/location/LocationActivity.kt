package com.joerakhimov.weather.location

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.joerakhimov.weather.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationActivity : AppCompatActivity() {

    private val locationViewModel: LocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        initObservers()
        input_search.addTextChangedListener {
            locationViewModel.setSearchQuery(it.toString())
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            locationViewModel.foundLocations.collect { locations ->
                recycler_locations.layoutManager = LinearLayoutManager(this@LocationActivity)
                recycler_locations.adapter = LocationAdapter(locations){ location ->
                    val data = Intent()
                    data.putExtra("LOCATION", location)
                    setResult(RESULT_OK)
                }
            }
        }
    }

}