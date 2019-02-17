package com.example.flaviogarcia.androideventskotlin.ui.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.widget.DatePicker
import java.util.*

class DatePickerDialog : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var onDateSetListener: (Calendar) -> Unit

    companion object {

        fun show(manager: FragmentManager, onDateSetListener: (Calendar) -> Unit) {
            val picker = DatePickerDialog()
            picker.onDateSetListener = onDateSetListener
            picker.show(manager, "datePicker")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val data = Calendar.getInstance()
        val dia = data[Calendar.DAY_OF_MONTH]
        val mes = data[Calendar.MONTH]
        val ano = data[Calendar.YEAR]
        return DatePickerDialog(activity, this, ano, mes, dia)
    }

    override fun onDateSet(view: DatePicker?, ano: Int, mes: Int, dia: Int) {
        val data = Calendar.getInstance()
        data.set(ano, mes, dia)
        onDateSetListener(data)
    }
}
