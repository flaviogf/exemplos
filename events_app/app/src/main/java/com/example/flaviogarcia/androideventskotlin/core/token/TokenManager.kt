package com.example.flaviogarcia.androideventskotlin.core.token

import android.content.Context
import com.example.flaviogarcia.androideventskotlin.R

class TokenManager(context: Context) {

    private val chavePreference = context.resources.getString(R.string.preference_key)
    private val chaveToken = context.resources.getString(R.string.token_key)
    private val preferences = context.getSharedPreferences(chavePreference, Context.MODE_PRIVATE)

    fun salvar(token: String) = preferences.edit().putString(chaveToken, "JWT $token").apply()

    fun ler(): String = preferences.getString(chaveToken, "")

    fun deletar() = preferences.edit().remove(chaveToken).apply()
}
