package com.example.flaviogarcia.androideventskotlin.util.extension

import java.util.*

fun Calendar.formatoBrasileiro(): String {
    val dia = this[Calendar.DAY_OF_MONTH].format("00")
    val mes = this[Calendar.MONTH].format("00")
    val ano = this[Calendar.YEAR]
    return "$dia/$mes/$ano"
}

fun Calendar.formatoApi(): String {
    val dia = this[Calendar.DAY_OF_MONTH].format("00")
    val mes = (this[Calendar.MONTH] + 1).format("00")
    val ano = this[Calendar.YEAR]
    return "$ano-$mes-$dia"
}
