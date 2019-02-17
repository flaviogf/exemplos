package com.example.flaviogarcia.androideventskotlin.dto

import com.example.flaviogarcia.androideventskotlin.model.Usuario

data class AutenticacaoResponseDto(val usuario: Usuario, val token: String)
