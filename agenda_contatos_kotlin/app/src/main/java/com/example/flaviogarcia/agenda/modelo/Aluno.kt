package com.example.flaviogarcia.agenda.modelo

import java.io.Serializable

class Aluno(
        val id: Int?,
        val nome: String,
        val endereco: String,
        val telefone: String,
        val site: String,
        val nota: Double
) : Serializable {
    override fun toString(): String = "$id - $nome"
}