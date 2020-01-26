package com.example.innowiseweatherapplication.view.viewImpl


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.adapter.TabsPagerAdapter
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.TodayWeatherClass
import com.example.innowiseweatherapplication.model.entity.WeatherClass
import com.example.innowiseweatherapplication.presenter.presenterImpl.MainPresenter
import com.example.innowiseweatherapplication.view.IMainView
import com.google.android.gms.location.*
import com.google.android.material.tabs.TabLayout
import java.util.*

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
       findViewById<TextView>(R.id.errorMessage).visibility=View.VISIBLE
       findViewById<Button>(R.id.retryBtn).visibility=View.VISIBLE
       println("Error is handled")
    }

    override fun showProgress() {
        findViewById<TextView>(R.id.errorMessage).visibility=View.INVISIBLE
        findViewById<Button>(R.id.retryBtn).visibility=View.INVISIBLE
        findViewById<ProgressBar>(R.id.progressBar).visibility=View.VISIBLE
        println("Progress is showed")
    }

    override fun hideProgress() {
        findViewById<ProgressBar>(R.id.progressBar).visibility=View.GONE
        println("Progress is hided")
    }

    override fun showLoadedWeather(provideWeather: WeatherClass?) {

        println(provideWeather?.list?.get(0)?.main?.temp)
    }

    override fun showNotConnectionMessage() {
        println("connection Error is showed")
    }

    override fun getLastLocation() {
            if (checkPermission()){
                if (isLocationEnabled()){
                    mFusedLocationClient.lastLocation.addOnCompleteListener {task ->
                            val location: Location? = task.result
                            if (location == null) {
                              requestNewLocationData()

                            } else {
                                val geocode = Geocoder(this, Locale.ENGLISH)
                                val addresses:List<Address> = geocode.getFromLocation(location.latitude,location.longitude,1)
                                if (addresses.isNotEmpty()) mainPresenter.getData(addresses[0].locality)
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

    override fun openTodayWeather(todayWeatherClass: TodayWeatherClass,arrayList: ArrayList<RecyclerItemWeatherClass>) {
        findViewById<ViewPager>(R.id.view_pager).visibility = View.VISIBLE
        findViewById<View>(R.id.divider_activity_main).visibility = View.VISIBLE
        findViewById<TabLayout>(R.id.tabs).visibility = View.VISIBLE

        val viewPagerAdapter =
            TabsPagerAdapter(
                supportFragmentManager,todayWeatherClass,arrayList
            )

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_01d_w)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_03d_w)

        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                println("${tab?.position} is selected")
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                println("${tab?.position} is unselected")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                println("${tab?.position} is reselected")

            }
        })


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
           // getLastLocation()
        }
    }

    fun isInternetConnection()= run {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        activeNetwork?.isConnectedOrConnecting == true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
