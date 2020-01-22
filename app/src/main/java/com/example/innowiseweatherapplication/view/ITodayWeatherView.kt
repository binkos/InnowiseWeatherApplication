package com.example.innowiseweatherapplication.view

import android.view.View

interface ITodayWeatherView {
    fun init(view: View)
    fun fillViews(
        speed: Int,
        deg: String
    )
}