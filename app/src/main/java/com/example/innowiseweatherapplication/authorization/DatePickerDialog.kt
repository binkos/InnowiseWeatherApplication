package com.example.innowiseweatherapplication.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.innowiseweatherapplication.R
import kotlinx.android.synthetic.main.date_picker_dialog.*

class DatePickerDialog(private val listener: (Int, Int, Int) -> Unit) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.date_picker_dialog, container)

        rootView.findViewById<Button>(R.id.acceptBtn).setOnClickListener {
            with(datePicker) { listener(dayOfMonth, month + 1, year) }
            dismiss()
        }
        return rootView
    }
}