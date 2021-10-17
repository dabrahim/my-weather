package tech.keyops.challenge.myweather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import tech.keyops.challenge.myweather.arch.domain.City
import tech.keyops.challenge.myweather.databinding.FragmentCitiesBinding
import tech.keyops.challenge.myweather.ui.adapter.CityAdapter
import tech.keyops.challenge.myweather.ui.viewmodel.CitiesViewModel

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private lateinit var binding: FragmentCitiesBinding
    private val viewModel: CitiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.cities.observe(viewLifecycleOwner) { cities ->
            onCitiesLoaded(cities)
        }

        viewModel.navigateToWeatherScreenEvent.observe(viewLifecycleOwner) { e ->
            e.getContentIfNotDispatchedOrReturnNull()?.let {
                navigateToWeatherScreen(it)
            }
        }
    }

    private fun onCitiesLoaded(cities: List<City>) {
        val cityAdapter = CityAdapter(cities) { index ->
            viewModel.onCityItemClick(index)
        }

        val dividerItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recyclerView.apply {
            adapter = cityAdapter
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun navigateToWeatherScreen(cityName: String) {
        findNavController()
            .navigate(
                CitiesFragmentDirections
                    .actionCitiesScreenToWeatherScreen(cityName)
            )
    }
}