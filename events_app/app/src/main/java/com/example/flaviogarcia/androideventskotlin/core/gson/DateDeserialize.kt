package com.example.flaviogarcia.androideventskotlin.core.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class DateDeserialize : JsonDeserializer<Calendar> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Calendar {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("pt", "br"))
        val data = dateFormat.parse(json?.asString)
        val calendar = Calendar.getInstance()
        calendar.time = data
        return calendar
    }
}
