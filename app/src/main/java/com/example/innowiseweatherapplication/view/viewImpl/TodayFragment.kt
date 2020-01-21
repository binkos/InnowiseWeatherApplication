package com.example.innowiseweatherapplication.view.viewImpl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.presenter.presenterImpl.TodayWeatherPresenter
import kotlinx.android.synthetic.main.today_weather_fragment_view.*

class TodayFragment:Fragment() {
    lateinit var todayWeatherPresenter:TodayWeatherPresenter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.today_weather_fragment_view,container,false)
        todayWeatherPresenter = TodayWeatherPresenter()
        init()
        view.findViewById<TextView>(R.id.share_TextView).setOnClickListener { v->
            run {
                    startActivity(todayWeatherPresenter.sendInfoBtnClicked("Hello from my test application."))
            }
        }
        return view
    }



    private fun init(){
        view?.findViewById<TextView>(R.id.share_TextView)?.setOnClickListener {
            run {
                startActivity(todayWeatherPresenter.sendInfoBtnClicked("hello"))
            }
        }
    }
}