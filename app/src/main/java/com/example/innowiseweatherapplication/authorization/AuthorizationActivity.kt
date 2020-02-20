package com.example.innowiseweatherapplication.authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.innowiseweatherapplication.R
import com.example.innowiseweatherapplication.view.viewImpl.MainActivity
import kotlinx.android.synthetic.main.authorization_layout.*

class AuthorizationActivity : BaseAuthorizationActivity() {

    companion object {

//        const val APP_PREFERENCES = "app settings"
//        const val USER_IN = "user in app"

        fun startActivity(context: Context) {
            val intent = Intent(context, AuthorizationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.authorization_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
//        if (sharedPreferences.getBoolean(USER_IN, false)) MainActivity.startActivity(this)

        loginBtn.setOnClickListener {
            if (isInputInfoValid()) {
//                val sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
//                val editor = sharedPreferences.edit()
//                editor.putBoolean(USER_IN, true)
//                editor.apply()
                MainActivity.startActivity(this, TypeOfEntry.SIMPLE)
            } else Toast.makeText(this, "You failed validation", Toast.LENGTH_LONG).show()
        }

        forgot_passTV.setOnClickListener { ForgotPasswordActivity.startActivity(this) }

        googleIV.setOnClickListener {
            MainActivity.startActivity(this, TypeOfEntry.EMAIL)
        }

        facebookIV.setOnClickListener {
            MainActivity.startActivity(this, TypeOfEntry.FACEBOOK)
        }

        VKIV.setOnClickListener {
            MainActivity.startActivity(this, TypeOfEntry.VK)
        }

        signUpTV.setOnClickListener {
            RegistrationActivity.startActivity(this)
        }
    }

    private fun isInputInfoValid(): Boolean {
        val loginRegex = Regex("""\w{3,16}""")
        val passRegex = Regex("""\w{8,16}""")

        if (loginET.text.toString() == "pupa" && passwordET.text.toString() == "pupalupa")
            return true

        return loginRegex.containsMatchIn(loginET.text.toString()) &&
                passRegex.containsMatchIn(passwordET.text.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}