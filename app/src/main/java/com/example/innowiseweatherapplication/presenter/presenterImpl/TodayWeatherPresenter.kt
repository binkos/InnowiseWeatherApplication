package com.example.innowiseweatherapplication.presenter.presenterImpl

import android.content.Intent
import com.example.innowiseweatherapplication.presenter.ITodayWeatherPresenter

class TodayWeatherPresenter:ITodayWeatherPresenter {

    override fun sendInfoBtnClicked(string: String):Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, string)
            type = "text/plain"
        }

        return Intent.createChooser(sendIntent, null)

    }
}