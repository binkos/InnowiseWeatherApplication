package com.example.innowiseweatherapplication.authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.innowiseweatherapplication.R
import kotlinx.android.synthetic.main.registration_layout.*

class RegistrationActivity : BaseAuthorizationActivity() {

    companion object {

        fun startActivity(context: Context) {
            val intent = Intent(context, RegistrationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.registration_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reg_Btn.setOnClickListener {
            if (isInputInfoValid() && pupaValidation()) {
                SecondRegistrationActivity.startActivity(this)
            } else Toast.makeText(this, "You failed validation", Toast.LENGTH_LONG).show()
        }
    }

    private fun isInputInfoValid(): Boolean {
        val emailRegex = Regex("""@""")
        val loginRegex = Regex("""\.{3,16}""")
        val passRegex = Regex("""\w{8,16}""")
        return loginRegex.containsMatchIn(login_EditText.toString()) &&
                passRegex.containsMatchIn(password_EditText.text.toString()) &&
                password_EditText.text.toString() == repPassword_EditText.text.toString() &&
                emailRegex.containsMatchIn(email_EditText.text.toString())
    }

    private fun pupaValidation() = login_EditText.text.toString() != "pupa"

}