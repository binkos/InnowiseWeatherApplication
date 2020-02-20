package com.example.innowiseweatherapplication.authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.innowiseweatherapplication.R
import kotlinx.android.synthetic.main.new_pass_layout.*

class NewPasswordActivity : BaseAuthorizationActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, NewPasswordActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.new_pass_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        create_Btn.setOnClickListener {
            if (isInputInfoValid()) AuthorizationActivity.startActivity(this)
        }
    }

    private fun isInputInfoValid(): Boolean {
        val passRegex = Regex("""\w{8,16}""")
        return passRegex.containsMatchIn(newPassword_EditText.text.toString()) &&
                newPassword_EditText.text.toString() == repeatPassword_EditText.text.toString()
    }
}