package com.example.innowiseweatherapplication.authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.innowiseweatherapplication.R
import kotlinx.android.synthetic.main.forgot_pass_layout.*

class ForgotPasswordActivity : BaseAuthorizationActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, ForgotPasswordActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.forgot_pass_layout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        input_Btn.setOnClickListener {
            if (isInputInfoValid()) {
                // send info to user's email or say this user with this login/e-mail doesn't exist doesn't exist
                NewPasswordActivity.startActivity(this)
            } else {
                Toast.makeText(this, "You failed validation", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isInputInfoValid(): Boolean {
        // login_email_EditText.text make request to db and check existing of this user if not check e-mail if don't exist e-mail than say about ERROR
        val emailRegex = Regex("""@""")
        return emailRegex.containsMatchIn(login_email_EditText.text.toString())
    }

}