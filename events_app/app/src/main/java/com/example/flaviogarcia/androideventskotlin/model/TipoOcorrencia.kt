package com.example.flaviogarcia.androideventskotlin.model

import android.os.Parcel
import android.os.Parcelable

class TipoOcorrencia(
        val _id: String,
        val descricao: String,
        val nomenclatura: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(descricao)
        parcel.writeString(nomenclatura)
    }

    override fun describeContents() = 0

    override fun toString() = nomenclatura

    companion object CREATOR : Parcelable.Creator<TipoOcorrencia> {
        override fun createFromParcel(parcel: Parcel): TipoOcorrencia = TipoOcorrencia(parcel)
        override fun newArray(size: Int): Array<TipoOcorrencia> = newArray(size)
    }
}
