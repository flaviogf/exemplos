package com.example.flaviogarcia.androideventskotlin.util.extension

fun String.limite(qtd: Int): String = if (this.length > qtd) {
    "${this.substring(0, qtd)}..."
} else {
    this
}
