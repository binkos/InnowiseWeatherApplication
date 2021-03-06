package com.example.innowiseweatherapplication.view.viewImpl

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.presenter.presenterImpl.TodayWeatherPresenter
import com.example.innowiseweatherapplication.view.ITodayWeatherView
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class TodayFragment:Fragment(),ITodayWeatherView {
    lateinit var todayWeatherPresenter:TodayWeatherPresenter
    lateinit var todayFragView:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        todayFragView = inflater.inflate(R.layout.today_weather_fragment_view,container,false)
        todayWeatherPresenter = TodayWeatherPresenter(this)
        init(todayFragView)
        return todayFragView
    }

    override fun init(view: View) {
        view.findViewById<TextView>(R.id.share_TextView).setOnClickListener {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hello, right now i am in ${arguments!!.getString("CITY_NAME")} and temperature here is ${arguments!!.getFloat("TEMP").roundToInt()}°C, we have ${arguments!!.getString("WEATHER_NAME")}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(sendIntent, null))
        }
        todayWeatherPresenter.init(
            arguments!!.getFloat("SPEED"),
            arguments!!.getInt("DEG")
        )
    }

    @SuppressLint("SetTextI18n")
    override fun fillViews(
        speed: Int,
        deg: String
    ) {
        val imageView = todayFragView.findViewById(R.id.imageView) as ImageView
        todayFragView.findViewById<TextView>(R.id.humidity_TextView)?.text = "${arguments!!.getInt("HUMIDITY")}%"
        todayFragView.findViewById<TextView>(R.id.speed_TextView)?.text = "$speed km/h"
        todayFragView.findViewById<TextView>(R.id.pressure_TextView)?.text = "${arguments!!.getInt("PRESSURE")} hPa"
        todayFragView.findViewById<TextView>(R.id.way_TextView)?.text = deg
        Picasso.get().load(arguments!!.getInt("ICON")).error(R.drawable.ic_01d_g).into(imageView)
        todayFragView.findViewById<TextView>(R.id.sea_lvl)?.text = "${arguments!!.getInt("SEA")}"
        todayFragView.findViewById<TextView>(R.id.weather_TextView)?.text = "${arguments!!.getFloat("TEMP").roundToInt()}°C | ${arguments!!.getString("WEATHER_NAME")}"
        todayFragView.findViewById<TextView>(R.id.city_TextView)?.text = "${arguments!!.getString("CITY_NAME")}, ${arguments!!.getString("COUNTRY_NAME")}"
    }


}