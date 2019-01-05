package com.example.flaviogarcia.androideventskotlin.service

import com.example.flaviogarcia.androideventskotlin.dto.CadastroOcorrenciaDto
import com.example.flaviogarcia.androideventskotlin.model.Ocorrencia
import retrofit2.Call
import retrofit2.http.*

interface OcorrenciaService {

    @GET("ocorrencia")
    fun listar(@Header("Authorization") token: String): Call<List<Ocorrencia>>

    @GET("ocorrencia/usuario")
    fun listarOcorrenciasUsuario(@Header("Authorization") token: String): Call<List<Ocorrencia>>

    @POST("ocorrencia")
    fun cadastrar(@Header("Authorization") token: String, @Body ocorrencia: CadastroOcorrenciaDto): Call<Void>

    @DELETE("ocorrencia/{id}")
    fun deletar(@Header("Authorization") token: String, @Path("id") id: String): Call<Void>

    @PUT("ocorrencia/{id}")
    fun atualizar(@Header("Authorization") token: String, @Body ocorrencia: CadastroOcorrenciaDto, @Path("id") id: String): Call<Void>
}
