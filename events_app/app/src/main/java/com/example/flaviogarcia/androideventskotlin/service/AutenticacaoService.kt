package com.example.flaviogarcia.androideventskotlin.service

import com.example.flaviogarcia.androideventskotlin.dto.AutenticacaoRequestDto
import com.example.flaviogarcia.androideventskotlin.dto.AutenticacaoResponseDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AutenticacaoService {

    @POST("login")
    fun login(@Body login: AutenticacaoRequestDto): Call<AutenticacaoResponseDto>
}
