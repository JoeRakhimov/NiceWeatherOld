package com.joerakhimov.weather.location

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joerakhimov.weather.R
import com.joerakhimov.weather.forecast.LocationItem
import kotlinx.android.synthetic.main.listitem_location.view.*

class LocationAdapter(
    private val locations: List<LocationItem>,
    private var onItemClicked: ((location: LocationItem) -> Unit)
) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listitem_location, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val location = locations[position]
        viewHolder.itemView.text_name.text = location.name
        viewHolder.itemView.text_country.text = location.country
        viewHolder.itemView.setOnClickListener {
            onItemClicked(location)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = locations.size

}