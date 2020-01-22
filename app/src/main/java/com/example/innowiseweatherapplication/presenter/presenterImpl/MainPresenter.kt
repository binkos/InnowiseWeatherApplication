package com.example.innowiseweatherapplication.presenter.presenterImpl

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.WeatherClass
import com.example.innowiseweatherapplication.model.modelImpl.MainModel
import com.example.innowiseweatherapplication.presenter.IMainPresenterInterface
import com.example.innowiseweatherapplication.view.IMainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view:IMainView):
    IMainPresenterInterface {
    private val model = MainModel()
    lateinit var weather:WeatherClass

    @SuppressLint("CheckResult")
    override fun getData(lat:Double, lon:Double) {
        view.showProgress()
        val dataObservable: Observable<WeatherClass> = model.getWeather(lat, lon)

        dataObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {it ->
                println("HERE")
                weather = it
                val arrayList = ArrayList<RecyclerItemWeatherClass>()
                it.list?.forEach {
                    arrayList.add(RecyclerItemWeatherClass(it.weather[0].icon,it.main!!.temp-273,it.weather[0].main,it.dtTxt as String))
                }
                view.hideProgress()
                view.openTodayWeather(weather,arrayList)},
                {t ->
                    view.showError()
                }
        )
    }

    fun isConnection(cm: ConnectivityManager): Boolean {
        var result = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = true
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = true
                    }
                }
            }
        } else {
            cm.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        result = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        result = true
                    }
                }
            }
        }
        println("$result from isConnectionInternet")
        return result
    }



}