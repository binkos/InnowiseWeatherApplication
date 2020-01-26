package com.example.innowiseweatherapplication.view.viewImpl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.adapter.WeatherRecyclerAdapter
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass

class ForecastFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.forecast_weather_fragment_view,container,false)
        println("Forecast Fragment")
        val recyclerView=view.findViewById<RecyclerView>(R.id.rec_view)
            recyclerView.adapter = WeatherRecyclerAdapter(this.requireContext(),
                arguments?.getParcelableArrayList<RecyclerItemWeatherClass>("ARRAY") as ArrayList<RecyclerItemWeatherClass>
            )

        recyclerView.layoutManager = LinearLayoutManager(activity)

        return view
    }

}