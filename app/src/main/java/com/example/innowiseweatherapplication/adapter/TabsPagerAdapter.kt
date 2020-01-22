package com.example.innowiseweatherapplication.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.WeatherClass
import com.example.innowiseweatherapplication.view.viewImpl.ErrorFragment
import com.example.innowiseweatherapplication.view.viewImpl.ForecastFragment
import com.example.innowiseweatherapplication.view.viewImpl.TodayFragment

class TabsPagerAdapter(fragmentManager: FragmentManager, private val weatherClass: WeatherClass,val arrayList: ArrayList<RecyclerItemWeatherClass>): FragmentPagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                val fragment = TodayFragment()
                val bundle = Bundle()

                bundle.putInt("HUMIDITY", weatherClass.list!![0].main!!.humidity)
                bundle.putFloat("SPEED", weatherClass.list!![0].wind!!.speed)
                bundle.putInt("DEG", weatherClass.list!![0].wind!!.deg)
                bundle.putFloat("TEMP", weatherClass.list!![0].main!!.temp-273)
                bundle.putString("WEATHER_NAME", weatherClass.list!![0].weather[0].main)
                bundle.putString("COUNTRY_NAME", weatherClass.city!!.country)
                bundle.putInt("PRESSURE", weatherClass.list!![0].main!!.pressure)
                bundle.putString("CITY_NAME", weatherClass.city!!.name)

                fragment.arguments = bundle

                return fragment
            }
            1->{
                val fragment = ForecastFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("ARRAY",arrayList)
                fragment.arguments = bundle
                return fragment
            }
        }
        return ErrorFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "Today"
            1->return "Forecast"
        }
        return "Error"
    }

    override fun getCount(): Int {
        return 2
    }

}