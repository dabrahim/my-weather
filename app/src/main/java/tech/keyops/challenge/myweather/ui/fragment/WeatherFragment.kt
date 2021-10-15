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

        // We subscribe to the weather data and display it as soon as we receive it
        viewModel.weather.observe(viewLifecycleOwner) { weather ->
            displayWeatherData(weather)
        }

        // We subscribe to the loader visibility event and show/hide the loader view accordingly
        viewModel.toggleLoaderVisibilityEvent.observe(viewLifecycleOwner) { e ->
            e.getContentIfNotDispatchedOrReturnNull()
                ?.let { // We make sure this event wasn't already dealt with
                    toggleLoaderVisibility(it)
                }
        }

        viewModel.showErrorViewEvent.observe(viewLifecycleOwner) { e ->
            e.getContentIfNotDispatchedOrReturnNull()?.let {
                showErrorView()
            }
        }
    }

    // Method to display the data to the view
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

    // Used to show / hide the loader view
    private fun toggleLoaderVisibility(show: Boolean) {
        binding.loaderLayer.visibility = if (show) View.VISIBLE else View.GONE
    }

    // Used to show the error view in case something bad happens
    private fun showErrorView() {
        binding.errorView.visibility = View.VISIBLE
    }
}