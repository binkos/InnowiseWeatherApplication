package com.example.innowiseweatherapplication.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.TodayWeatherClass
import com.example.innowiseweatherapplication.view.viewImpl.ErrorFragment
import com.example.innowiseweatherapplication.view.viewImpl.ForecastFragment
import com.example.innowiseweatherapplication.view.viewImpl.TodayFragment

class TabsPagerAdapter(fragmentManager: FragmentManager, private val todayWeatherClass: TodayWeatherClass,
                       private val arrayList: ArrayList<RecyclerItemWeatherClass>):
    FragmentPagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0->{
                val fragment = TodayFragment()
                val bundle = Bundle()

                bundle.putInt("HUMIDITY", todayWeatherClass.humidity)
                bundle.putFloat("SPEED", todayWeatherClass.speed)
                bundle.putInt("DEG", todayWeatherClass.deg)
                bundle.putFloat("TEMP", todayWeatherClass.temp)
                bundle.putInt("SEA",todayWeatherClass.seaLevel)
                bundle.putString("WEATHER_NAME", todayWeatherClass.weatherName)
                bundle.putInt("ICON",getResIdIcon(todayWeatherClass.icon))
                bundle.putString("COUNTRY_NAME", todayWeatherClass.countryName)
                bundle.putInt("PRESSURE", todayWeatherClass.pressure)
                bundle.putString("CITY_NAME", todayWeatherClass.cityName)

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

    private fun getResIdIcon(name:String)=when(name){
        "04d"->{R.drawable.ic_04_g}
        "04n"->{R.drawable.ic_04_g}
        "01d"->{R.drawable.ic_01d_g}
        "01n"->{R.drawable.ic_01n_g}
        "02d"->{R.drawable.ic_01d_g}
        "02n"->{R.drawable.ic_01n_g}
        "10d"->{R.drawable.ic_10_g}
        "10n"->{R.drawable.ic_10_g}
        "13d"->{R.drawable.ic_13_g}
        "13n"->{R.drawable.ic_13_g}
        "03d"->{R.drawable.ic_03d_g}
        "03n"->{R.drawable.ic_03n_g}

        else->{2}
    }

}