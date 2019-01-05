package com.example.flaviogarcia.androideventskotlin.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AtualizarOcorrenciaCallback(private val callback: (String?, Int?) -> Unit) : Callback<Void> {

    override fun onFailure(call: Call<Void>?, t: Throwable?) {
        callback("Não foi possível atualizar a ocorrência", null)
    }

    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
        response?.apply {
            when (code()) {
                204 -> callback("Ocorrencia atualizada com sucesso", 204)
                400 -> callback("Verifique os dados informados", 400)
                401 -> callback("Token expirado", 401)
                else -> callback("Não foi possível cadastrar a ocorrência", null)
            }
        }
    }
}
