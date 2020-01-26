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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
                arrayList.add(RecyclerItemWeatherClass(RecyclerItemWeatherClass.HEADER_TYPE,day="Today"))
                it.list?.forEach {

                    val array = parseFunction(it.dtTxt!!)
                    val parceledDay = anotherParcelFunction(array[1])

                    if (array[0]==0&&arrayList.size!=1) arrayList.add(RecyclerItemWeatherClass(RecyclerItemWeatherClass.HEADER_TYPE, day = parceledDay))
                    if (array[0]==21){
                    arrayList.add(RecyclerItemWeatherClass(RecyclerItemWeatherClass.WEATHER_TYPE_WITHOUT_DIVIDERS,it.weather[0].icon,
                        it.main!!.temp-273,it.weather[0].main,parceledDay,array[0]))
                    }
                    else{
                    arrayList.add(RecyclerItemWeatherClass(RecyclerItemWeatherClass.WEATHER_TYPE,it.weather[0].icon,
                        it.main!!.temp-273,it.weather[0].main,parceledDay,array[0]))
                    }

                }
                view.hideProgress()
                view.openTodayWeather(weather,arrayList)
            },
                {t ->
                    println(t.message)
                    view.hideProgress()
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

    private fun parseFunction(dateInString:String):IntArray{
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT)
        formatter.timeZone = TimeZone.getDefault()

        val date: Date = formatter.parse(dateInString) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        println(calendar.get(Calendar.HOUR_OF_DAY))
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return intArrayOf(hourOfDay,dayOfWeek)
    }

    private fun anotherParcelFunction(date:Int):String = when(date){
        1->"Sunday"
        2->"Monday"
        3->"Tuesday"
        4->"Wednesday"
        5->"Thursday"
        6->"Friday"
        7->"Saturday"
        else -> "ERROR"
    }

}