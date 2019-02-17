package com.example.flaviogarcia.androideventskotlin.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeletarOcorrenciaCallback(private val callback: (String?) -> Unit) : Callback<Void> {
    override fun onFailure(call: Call<Void>?, t: Throwable?) {
        callback("Ocorreu um erro")
    }

    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
        response?.apply {
            when (code()) {
                204 -> callback(null)
                else -> callback("Não foi possível excluir a ocorrência")
            }
        }
    }
}
