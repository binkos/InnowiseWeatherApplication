package com.example.innowiseweatherapplication.presenter

import android.content.Intent

interface ITodayWeatherPresenter {
    fun sendInfoBtnClicked(string: String):Intent
}