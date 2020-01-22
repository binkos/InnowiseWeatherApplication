package com.example.innowiseweatherapplication.presenter.presenterImpl

import android.content.Intent
import com.example.innowiseweatherapplication.presenter.ITodayWeatherPresenter
import com.example.innowiseweatherapplication.view.ITodayWeatherView
import kotlin.math.roundToInt

class TodayWeatherPresenter(val view:ITodayWeatherView):ITodayWeatherPresenter {

    override fun sendInfoBtnClicked(string: String):Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, string)
            type = "text/plain"
        }
        return Intent.createChooser(sendIntent, null)
    }

    override fun init(
        speed: Float,
        deg: Int
    ) {

        view.fillViews(convertMetresToKiloMetres(speed),
            convertDeg(deg))
    }

    private fun convertMetresToKiloMetres(speed: Float)= (speed * (3600 / 1000)).roundToInt()

    private fun convertDeg(deg: Int):String{
        return when (deg) {
            in 1..90 -> "NE"
            90 ->  "E"
            in 91..179 ->  "SE"
            180 ->  "S"
            in 181..269 ->  "SW"
            270 ->  "W"
            in 271..359 -> "NW"
            360 -> "N"
            else -> ""
        }

    }

}