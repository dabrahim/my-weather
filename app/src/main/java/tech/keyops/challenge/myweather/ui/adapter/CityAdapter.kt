package tech.keyops.challenge.myweather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.keyops.challenge.myweather.arc.data.source.City
import tech.keyops.challenge.myweather.databinding.CityItemBinding

class CityAdapter(private val cities: List<City>, val onItemClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    class ViewHolder(val binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.cityName.text = city.name
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CityItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener { onItemClick(position) }
        val city = getItem(position)
        holder.bind(city)
    }

    override fun getItemCount(): Int = cities.size

    private fun getItem(position: Int) = cities[position]
}
