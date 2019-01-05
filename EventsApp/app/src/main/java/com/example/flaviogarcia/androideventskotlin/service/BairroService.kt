package com.example.flaviogarcia.androideventskotlin.service

import com.example.flaviogarcia.androideventskotlin.model.Bairro
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface BairroService {

    @GET("bairro")
    fun listar(@Header("Authorization") token: String): Call<List<Bairro>>
}
