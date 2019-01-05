package com.example.flaviogarcia.androideventskotlin.dto

import com.example.flaviogarcia.androideventskotlin.util.extension.formatoApi
import java.util.*

data class CadastroOcorrenciaDto(
        var resumo: String = "",
        var periodo: String = "",
        var numero: Int = 0,
        var bairro: String = "",
        var tipoOcorrencia: String = "",
        var dataAcontecimento: String = Calendar.getInstance().formatoApi()
)
