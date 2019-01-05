package com.example.flaviogarcia.androideventskotlin.callback

import com.example.flaviogarcia.androideventskotlin.model.TipoOcorrencia
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaTipoOcorrenciaCallback(private val callback: (String?, List<TipoOcorrencia>?) -> Unit) : Callback<List<TipoOcorrencia>> {

    override fun onFailure(call: Call<List<TipoOcorrencia>>?, t: Throwable?) {
        callback("Não foi possível listar os tipos de ocorrências", null)
    }

    override fun onResponse(call: Call<List<TipoOcorrencia>>?, response: Response<List<TipoOcorrencia>>?) {
        response?.apply {
            when (code()) {
                200 -> callback(null, body())
                401 -> callback("Token expirado", null)
                else -> callback("Não foi possível listar os tipos de ocorrências", null)
            }
        }
    }
}
