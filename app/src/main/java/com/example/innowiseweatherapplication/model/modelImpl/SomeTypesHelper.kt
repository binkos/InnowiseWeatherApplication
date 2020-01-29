package com.example.innowiseweatherapplication.model.modelImpl

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Looper
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.location.*
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class SomeTypesHelper(private val context:Context) {
    var mFusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private var mLocation:Location? = null


    fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1
    println("HERE")
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            println("Current latitude " + mLastLocation.latitude)
            println("Current longitude " + mLastLocation.longitude)
        }
    }

    fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    fun checkPermission():Boolean {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            println("true")
            return true
        }
        println("false")
        return false
    }

    fun isConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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

    fun getCityWhereYouAre():String {
        println("We are here")
        val geocode = Geocoder(context, Locale.ENGLISH)
        if (mLocation!!.longitude<0||mLocation!!.latitude<0){
            return "Error"
        }
        val addresses:List<Address> = geocode.getFromLocation(mLocation!!.latitude,mLocation!!.longitude,1)
        return if (addresses.isNotEmpty()) addresses[0].locality
        else "Error"
    }


    fun isLocationNull(location:Location?) = if (location==null){
        println("Location NUll")
        true
    }else{
        println("Location not null")
        mLocation=location
        false
    }

    fun createNewIntent(){
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(context,intent,null)
    }



    fun parseFunction(dateInString:String):IntArray{
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT)
        formatter.timeZone = TimeZone.getDefault()
        val date: Date = formatter.parse(dateInString) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return intArrayOf(hourOfDay,dayOfWeek)
    }

    fun anotherParcelFunction(date:Int):String = when(date){
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