package com.example.flaviogarcia.androideventskotlin.callback

import com.example.flaviogarcia.androideventskotlin.dto.AutenticacaoResponseDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutenticacaoCallback(val callback: (String?, AutenticacaoResponseDto?) -> Unit) : Callback<AutenticacaoResponseDto> {

    override fun onFailure(call: Call<AutenticacaoResponseDto>?, t: Throwable?) {
        callback("Não foi possível autenticar", null)
    }

    override fun onResponse(call: Call<AutenticacaoResponseDto>?, response: Response<AutenticacaoResponseDto>?) {
        response?.apply {
            when (code()) {
                200 -> callback(null, body())
                400 -> callback("Email ou senha inválidos", null)
                401 -> callback("Usuário não encontrado", null)
                else -> callback("Não foi possível autenticar", null)
            }
        }
    }
}
