package com.example.flaviogarcia.androideventskotlin.core.retrofit

import com.example.flaviogarcia.androideventskotlin.core.gson.DateDeserialize
import com.example.flaviogarcia.androideventskotlin.service.AutenticacaoService
import com.example.flaviogarcia.androideventskotlin.service.BairroService
import com.example.flaviogarcia.androideventskotlin.service.OcorrenciaService
import com.example.flaviogarcia.androideventskotlin.service.TipoOcorrenciaService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetrofitConfig {

    private val urlApiDev = "http://192.168.10.205:3000/api/"

    private val gson = GsonBuilder()
            .registerTypeAdapter(Calendar::class.java, DateDeserialize())
            .create()

    private val log = HttpLoggingInterceptor()
            .setLevel(BODY)

    private val cliente = OkHttpClient.Builder()
            .addInterceptor(log)
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(urlApiDev)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(cliente)
            .build()

    val tipoOcorrenciaService: TipoOcorrenciaService = retrofit.create(TipoOcorrenciaService::class.java)
    val autenticacaoService: AutenticacaoService = retrofit.create(AutenticacaoService::class.java)
    val ocorrenciaService: OcorrenciaService = retrofit.create(OcorrenciaService::class.java)
    val bairroService: BairroService = retrofit.create(BairroService::class.java)
}
