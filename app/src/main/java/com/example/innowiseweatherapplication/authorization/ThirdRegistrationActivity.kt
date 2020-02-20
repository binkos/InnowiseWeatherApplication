package com.example.innowiseweatherapplication.authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.innowiseweatherapplication.R
import kotlinx.android.synthetic.main.registration_third_layout.*

class ThirdRegistrationActivity : BaseAuthorizationActivity() {

    override fun getLayout() = R.layout.registration_third_layout

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ThirdRegistrationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reg_Btn.setOnClickListener {
            AuthorizationActivity.startActivity(this)
        }
    }
}