package com.example.innowiseweatherapplication.view.viewImpl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.innowiseweatherapplication.R

class TodayFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.today_weather_fragment_view,container,false)
        println(arguments?.getInt("HUMIDITY"))
        println(arguments?.getFloat("SPEED"))
        println(arguments?.getInt("DEG"))
        println(arguments?.getFloat("TEMP"))
        println(arguments?.getString("WEATHER_NAME"))
        println(arguments?.getString("COUNTRY_NAME"))
        println(arguments?.getString("CITY_NAME"))
        println(arguments?.getInt("PRESSURE"))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}