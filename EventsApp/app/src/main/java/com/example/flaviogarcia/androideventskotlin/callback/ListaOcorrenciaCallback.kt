package com.example.flaviogarcia.androideventskotlin.callback

import com.example.flaviogarcia.androideventskotlin.model.Ocorrencia
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaOcorrenciaCallback(private val callback: (String?, List<Ocorrencia>?) -> Unit) : Callback<List<Ocorrencia>> {

    override fun onFailure(call: Call<List<Ocorrencia>>?, t: Throwable?) {
        callback("Não foi possível buscar as ocorrências", null)
    }

    override fun onResponse(call: Call<List<Ocorrencia>>?, response: Response<List<Ocorrencia>>?) {
        response?.apply {
            when (code()) {
                200 -> callback(null, body())
                401 -> callback("Token expirado", null)
                else -> callback("Não foi possível buscar as ocorrências", null)
            }
        }
    }
}
