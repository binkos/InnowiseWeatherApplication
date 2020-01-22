package com.example.innowiseweatherapplication.view.viewImpl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass

class ForecastFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.forecast_weather_fragment_view,container,false)
        println("Forecast Fragment")
        arguments!!.getParcelableArrayList<RecyclerItemWeatherClass>("ARRAY")?.forEach { it ->
            run {
                    println(it.name)
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }

}