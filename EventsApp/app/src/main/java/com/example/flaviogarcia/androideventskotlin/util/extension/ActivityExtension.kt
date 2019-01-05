package com.example.flaviogarcia.androideventskotlin.util.extension

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.flaviogarcia.androideventskotlin.R

fun AppCompatActivity.configurarToolbar(toolbar: Toolbar, titulo: String? = null, upNavigation: Boolean = false) {
    val tituloPadrao = resources.getString(R.string.app_name)
    title = titulo ?: tituloPadrao
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(upNavigation)
}

fun AppCompatActivity.configurarFragments(vararg fragments: Pair<Int, Fragment>) {
    val transaction = supportFragmentManager.beginTransaction()
    fragments.forEach { (key, fragment) ->
        transaction.add(key, fragment)
    }
    transaction.commit()
}
