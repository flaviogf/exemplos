package com.example.flaviogarcia.androideventskotlin.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Ocorrencia(
        val _id: String,
        val resumo: String,
        val periodo: String,
        val numero: Int,
        val dataAcontecimento: Calendar,
        val usuario: Usuario,
        val bairro: Bairro,
        val tipoOcorrencia: TipoOcorrencia
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readValue(Calendar::class.java.classLoader) as Calendar,
            parcel.readValue(Usuario::class.java.classLoader) as Usuario,
            parcel.readValue(Bairro::class.java.classLoader) as Bairro,
            parcel.readValue(TipoOcorrencia::class.java.classLoader) as TipoOcorrencia
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(resumo)
        parcel.writeString(periodo)
        parcel.writeInt(numero)
        parcel.writeValue(dataAcontecimento)
        parcel.writeValue(usuario)
        parcel.writeValue(bairro)
        parcel.writeValue(tipoOcorrencia)
    }

    override fun describeContents(): Int = Parcelable.CONTENTS_FILE_DESCRIPTOR

    companion object CREATOR : Parcelable.Creator<Ocorrencia> {
        override fun createFromParcel(parcel: Parcel): Ocorrencia = Ocorrencia(parcel)
        override fun newArray(size: Int): Array<Ocorrencia> = newArray(size)
    }
}
