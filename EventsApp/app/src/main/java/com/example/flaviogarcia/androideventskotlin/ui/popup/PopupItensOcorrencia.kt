package com.example.flaviogarcia.androideventskotlin.ui.popup

import android.content.Context
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import com.example.flaviogarcia.androideventskotlin.R

class PopupItensOcorrencia(private val context: Context, private val view: View) {

    fun show(onMenuItemClickListener: (MenuItem) -> Boolean) = with(PopupMenu(context, view)) {
        menuInflater.inflate(R.menu.menu_lista_ocorrencias_fragment, menu)
        setOnMenuItemClickListener(onMenuItemClickListener)
        show()
    }
}
