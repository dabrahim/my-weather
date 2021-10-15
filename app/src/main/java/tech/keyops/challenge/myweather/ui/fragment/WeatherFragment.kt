package tech.keyops.challenge.myweather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import tech.keyops.challenge.myweather.R
import tech.keyops.challenge.myweather.arc.data.source.Weather
import tech.keyops.challenge.myweather.arc.data.source.remote.Config
import tech.keyops.challenge.myweather.databinding.FragmentWeatherBinding
import tech.keyops.challenge.myweather.ui.viewmodel.WeatherViewModel

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            displayWeatherData(weather)
        }

        viewModel.toggleLoaderVisibilityEvent.observe(viewLifecycleOwner) { e ->
            e.getContentIfNotDispatchedOrReturnNull()?.let {
                toggleLoaderVisibility(it)
            }
        }
    }

    private fun displayWeatherData(weather: Weather) {
        binding.apply {
            temperature.text =
                requireContext().getString(R.string.temperature_template, weather.temperature)

            city.text = weather.cityName

            Glide.with(requireContext())
                .load(Config.getIconPath(weather.iconId))
                .into(icon)
        }
    }

    private fun toggleLoaderVisibility(show: Boolean) {
        binding.loaderLayer.visibility = if (show) View.VISIBLE else View.GONE
    }
}