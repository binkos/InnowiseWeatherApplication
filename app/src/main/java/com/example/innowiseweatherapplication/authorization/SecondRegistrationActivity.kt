package com.example.innowiseweatherapplication.authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.innowiseweatherapplication.R
import kotlinx.android.synthetic.main.registration_second_layout.*

class SecondRegistrationActivity : BaseAuthorizationActivity() {

    var genders = arrayOf(
        "Agender",
        "Androgyne",
        "Androgynous",
        "Bigender",
        "Cis",
        "FTM",
        "Gender Fluid",
        "Gender Nonconforming",
        "Gender Questioning",
        "Gender Variant",
        "Genderqueer",
        "Neutrois",
        "Non-binary",
        "Pangender",
        "Two-spirit",
        "Other"
    )

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SecondRegistrationActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayout() = R.layout.registration_second_layout

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Toast.makeText(
            this,
            "You get message with link on your email and approve your account",
            Toast.LENGTH_LONG
        ).show()

        continue_Btn.setOnClickListener {
            ThirdRegistrationActivity.startActivity(this)
        }

        DatePicker_EditText.setOnClickListener {
            DatePickerDialog(listener).show(
                supportFragmentManager,
                "TITLE"
            )
        }

        val aa = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, genders)
        GenderPicker_Spinner.adapter = aa
    }

    private val listener: (Int, Int, Int) -> Unit = { day, month, year ->
        DatePicker_EditText.setText("$day.$month.$year")
    }
}