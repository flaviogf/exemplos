package com.example.flaviogarcia.androideventskotlin.callback

import com.example.flaviogarcia.androideventskotlin.model.Bairro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaBairroCallback(private val callback: (String?, List<Bairro>?) -> Unit) : Callback<List<Bairro>> {

    override fun onFailure(call: Call<List<Bairro>>?, t: Throwable?) {
        callback("Não foi possível buscar os bairros", null)
    }

    override fun onResponse(call: Call<List<Bairro>>?, response: Response<List<Bairro>>?) {
        response?.apply {
            when (code()) {
                200 -> callback(null, body())
                401 -> callback("Token expirado", null)
                else -> callback("Não foi possível buscar os bairros", null)
            }
        }
    }
}
