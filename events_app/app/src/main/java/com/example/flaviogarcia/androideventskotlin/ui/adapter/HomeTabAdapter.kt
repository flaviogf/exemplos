package com.example.flaviogarcia.androideventskotlin.ui.adapter

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.flaviogarcia.androideventskotlin.model.ListaOcorrenciasTipo
import com.example.flaviogarcia.androideventskotlin.ui.fragments.ListaOcorrenciasFragment

class HomeTabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val tabs = arrayOf(
            "Ultimas Ocorrências" to ListaOcorrenciasFragment.instance(ListaOcorrenciasTipo.Todas),
            "Minhas Ocorrência" to ListaOcorrenciasFragment.instance(ListaOcorrenciasTipo.Usuario)
    )

    override fun getPageTitle(position: Int) = tabs[position].first

    override fun getItem(position: Int) = tabs[position].second

    override fun getCount() = tabs.size
}
