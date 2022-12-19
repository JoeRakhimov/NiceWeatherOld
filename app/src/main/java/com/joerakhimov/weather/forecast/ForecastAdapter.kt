package com.joerakhimov.weather.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.weather.R
import kotlinx.android.synthetic.main.listitem_forecast.view.*

class ForecastAdapter(private val forecast: List<ForecastdayItem>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listitem_forecast, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.itemView.text_date.text = forecast[position].date
        viewHolder.itemView.text_temp.text = "${forecast[position].mintempC}°-${forecast[position].maxtempC}°"
        viewHolder.itemView.text_condition.text = forecast[position].condition?.text
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = forecast.size

}