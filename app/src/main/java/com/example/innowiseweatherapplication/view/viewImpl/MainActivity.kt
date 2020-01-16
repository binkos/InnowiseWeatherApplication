package com.example.innowiseweatherapplication.view.viewImpl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.presenter.presenterImpl.MainPresenter
import com.example.innowiseweatherapplication.view.IMainView

class MainActivity : AppCompatActivity(),
    IMainView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainPresenter = MainPresenter(this)
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadedWeather() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNotConnectionMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
