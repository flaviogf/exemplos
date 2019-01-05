package com.example.flaviogarcia.androideventskotlin.util.extension

import java.text.DecimalFormat

fun Int.format(pattern: String): String {
    val format = DecimalFormat(pattern)
    return format.format(this)
}
