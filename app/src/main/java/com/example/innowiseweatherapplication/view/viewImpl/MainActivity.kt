package com.example.innowiseweatherapplication.view.viewImpl


import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.adapter.TabsPagerAdapter
import com.example.innowiseweatherapplication.model.entity.RecyclerItemWeatherClass
import com.example.innowiseweatherapplication.model.entity.TodayWeatherClass
import com.example.innowiseweatherapplication.model.modelImpl.SomeTypesHelper
import com.example.innowiseweatherapplication.presenter.presenterImpl.MainPresenter
import com.example.innowiseweatherapplication.view.IMainView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import java.util.*

class MainActivity : AppCompatActivity(), IMainView {
    private val ID = 42
    private lateinit var mFusedLocationClient:FusedLocationProviderClient
    private lateinit var mainPresenter:MainPresenter
    private lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout
    private lateinit var errorSnackbar:Snackbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        mainPresenter.getLastLocation()
    }

    override fun init(){
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val someTypesHelper = SomeTypesHelper(this)
        mainPresenter = MainPresenter(this,someTypesHelper)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)

        findViewById<Button>(R.id.retryBtn).setOnClickListener {
            errorSnackbar.dismiss()

            mainPresenter.getLastLocation()
        }
    }

    override fun showError(errorType:String) {
        findViewById<ImageView>(R.id.memIMG).visibility=View.VISIBLE
        findViewById<Button>(R.id.retryBtn).visibility=View.VISIBLE
        errorSnackbar = Snackbar.make(findViewById<View>(R.id.main_activity),errorType,Snackbar.LENGTH_INDEFINITE)
        errorSnackbar.show()
       println("Error is handled")
    }

    override fun showProgress() {
        findViewById<Button>(R.id.retryBtn).visibility=View.INVISIBLE
        findViewById<ImageView>(R.id.memIMG).visibility=View.INVISIBLE
        findViewById<ProgressBar>(R.id.progressBar).visibility=View.VISIBLE

        println("Progress is showed")
    }

    override fun hideProgress() {

        findViewById<ProgressBar>(R.id.progressBar).visibility=View.GONE

        println("Progress is hided")
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
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_01d_b)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_03d)

        tabLayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab==tabLayout.getTabAt(0)) tab!!.setIcon(R.drawable.ic_01d_b)
                else tab!!.setIcon(R.drawable.ic_03d_b)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                if (tab==tabLayout.getTabAt(0)){tab!!.setIcon(R.drawable.ic_01d)}
                else tab!!.setIcon(R.drawable.ic_03d)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                println("${tab?.position} is reselected")

            }
        })

    }

    override fun requestPermission() {
        println("try to request permission")
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            ID
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ){
        if (requestCode == ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                  mainPresenter.getLastLocation()
            }
            else{
                showError("You didn't give geoPermission for us")
                println("Conductor! We have a trouble!!!")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }
}
