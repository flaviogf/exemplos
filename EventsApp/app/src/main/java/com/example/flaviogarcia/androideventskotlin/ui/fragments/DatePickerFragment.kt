package com.example.flaviogarcia.androideventskotlin.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flaviogarcia.androideventskotlin.R
import com.example.flaviogarcia.androideventskotlin.ui.dialog.DatePickerDialog
import com.example.flaviogarcia.androideventskotlin.util.extension.formatoBrasileiro
import kotlinx.android.synthetic.main.fragment_date_picker.*
import java.util.*

class DatePickerFragment : Fragment() {

    private lateinit var onClickListener: (Calendar) -> Unit
    private var dataInicial: Calendar? = null

    companion object {

        fun instance(data: Calendar?, onClickListener: (Calendar) -> Unit): Fragment {
            val fragment = DatePickerFragment()
            fragment.onClickListener = onClickListener
            fragment.dataInicial = data
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_date_picker, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurar()
    }

    private fun configurar() = with(fragment_date_picker_edit_text) {
        val data = dataInicial ?: Calendar.getInstance()
        onClickListener(data)
        setText(data.formatoBrasileiro())
        setOnClickListener {
            DatePickerDialog.show(activity!!.supportFragmentManager) {
                setText(it.formatoBrasileiro())
                onClickListener(it)
            }
        }
    }
}
