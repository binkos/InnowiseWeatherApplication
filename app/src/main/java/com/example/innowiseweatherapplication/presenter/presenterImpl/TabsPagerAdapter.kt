package com.example.innowiseweatherapplication.presenter.presenterImpl

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.innowiseweatherapplication.view.viewImpl.ErrorFragment
import com.example.innowiseweatherapplication.view.viewImpl.ForecastFragment
import com.example.innowiseweatherapplication.view.viewImpl.TodayFragment

class TabsPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0->return TodayFragment()
            1->return ForecastFragment()
        }
        return ErrorFragment()
    }

    override fun getCount(): Int {
        return 2
    }

}