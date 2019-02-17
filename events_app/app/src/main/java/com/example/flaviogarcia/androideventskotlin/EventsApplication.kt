package com.example.flaviogarcia.androideventskotlin

import android.app.Application
import com.example.flaviogarcia.androideventskotlin.core.retrofit.RetrofitConfig

class EventsApplication : Application() {

    companion object {

        private val retrofit = RetrofitConfig()

        val tipoOcorrenciaService = retrofit.tipoOcorrenciaService
        val autenticacaoService = retrofit.autenticacaoService
        val ocorrenciaService = retrofit.ocorrenciaService
        val bairroService = retrofit.bairroService
    }
}
