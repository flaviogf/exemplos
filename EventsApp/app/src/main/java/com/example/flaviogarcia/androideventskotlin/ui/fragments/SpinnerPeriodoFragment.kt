package com.example.flaviogarcia.androideventskotlin.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.flaviogarcia.androideventskotlin.R
import kotlinx.android.synthetic.main.fragment_spinner_periodo.*

class SpinnerPeriodoFragment : Fragment() {

    private var periodo: String? = null

    companion object {

        fun instance(periodo: String?): SpinnerPeriodoFragment {
            val fragment = SpinnerPeriodoFragment()
            fragment.periodo = periodo
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_spinner_periodo, parent, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configurar()
    }

    private fun configurar() {
        val adapter = ArrayAdapter.createFromResource(context, R.array.spinner_periodo, android.R.layout.simple_dropdown_item_1line)
        fragment_spinner_periodo_spinner.adapter = adapter
        iniciar()
    }

    private fun iniciar() {
        for (posicao in 0 until fragment_spinner_periodo_spinner.count) {
            fragment_spinner_periodo_spinner.getItemAtPosition(posicao)?.takeIf { it.toString() == periodo }?.also {
                fragment_spinner_periodo_spinner.setSelection(posicao)
            }
        }
    }
}
