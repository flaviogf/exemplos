package com.example.flaviogarcia.androideventskotlin.service

import com.example.flaviogarcia.androideventskotlin.model.TipoOcorrencia
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TipoOcorrenciaService {

    @GET("tipoOcorrencia")
    fun listar(@Header("Authorization") token: String): Call<List<TipoOcorrencia>>
}
