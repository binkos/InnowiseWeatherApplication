package com.example.innowiseweatherapplication.view.viewImpl


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.adapter.TabsPagerAdapter
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.WeatherClass
import com.example.innowiseweatherapplication.presenter.presenterImpl.MainPresenter
import com.example.innowiseweatherapplication.view.IMainView
import com.google.android.gms.location.*
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), IMainView {
    private val PERMISSION_ID = 42
    private lateinit var mFusedLocationClient:FusedLocationProviderClient
    private lateinit var mainPresenter:MainPresenter
    private lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)




    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        println("Progress is showed")
    }

    override fun hideProgress() {
        println("Progress is hided")
    }

    override fun showLoadedWeather(provideWeather: WeatherClass?) {
        println(provideWeather?.list?.get(0)?.main?.temp)
    }

    override fun showNotConnectionMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLastLocation() {
        if (checkPermission()){
            if (isLocationEnabled()){
                mFusedLocationClient.lastLocation.addOnCompleteListener {task ->

                        val location: Location? = task.result
                        if (location == null) {
                          requestNewLocationData()
                        } else {
                            mainPresenter.getData(location.latitude,location.longitude)
                        }
                }
            }else{
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }else{
            println("beforeCheckingPermission")
            requestPermission()
        }
    }

    override fun openTodayWeather(weatherClass: WeatherClass,arrayList: ArrayList<RecyclerItemWeatherClass>) {
        val viewPagerAdapter =
            TabsPagerAdapter(
                supportFragmentManager,weatherClass,arrayList
            )

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_sun_white)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.weather_partly_cloudy)
    }

    override fun checkPermission():Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            println("true")
            return true
        }
        println("false")
        return false
    }

    override fun requestPermission() {
        println("try to request permission")
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    override fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                  getLastLocation()
            }
        }
    }

    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
              println("current latitude " + mLastLocation.latitude)
        }
    }

}
