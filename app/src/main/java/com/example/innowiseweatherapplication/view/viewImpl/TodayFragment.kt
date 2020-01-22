package com.example.innowiseweatherapplication.view.viewImpl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.presenter.presenterImpl.TodayWeatherPresenter
import com.example.innowiseweatherapplication.view.ITodayWeatherView
import kotlinx.android.synthetic.main.today_weather_fragment_view.*

class TodayFragment:Fragment(),ITodayWeatherView {
    lateinit var todayWeatherPresenter:TodayWeatherPresenter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.today_weather_fragment_view,container,false)
        todayWeatherPresenter = TodayWeatherPresenter(this)
        init(view)
        return view
    }

    override fun init(view: View) {
        view.findViewById<TextView>(R.id.share_TextView).setOnClickListener {
            run {
                startActivity(todayWeatherPresenter.sendInfoBtnClicked("Hello from my test application."))
            }
        }
        todayWeatherPresenter.init(
            arguments!!.getFloat("SPEED"),
            arguments!!.getInt("DEG")
        )
    }

    override fun fillViews(
        speed: Int,
        deg: String
    ) {
        println("$speed hello from fillViews")
        humidity_TextView.text = "${arguments!!.getInt("HUMIDITY")}%"
        speed_TextView.text = speed.toString()
        pressure_TextView.text = "${arguments!!.getInt("PRESSURE")}%"
        temp_kf_TextView.text = "${arguments!!.getFloat("TEMP")}"
        weather_TextView.text = "${arguments!!.getString("WEATHER_NAME")}"
        city_TextView.text = "${arguments!!.getString("CITY_NAME")}"
    }


}