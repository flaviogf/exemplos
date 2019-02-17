package com.example.flaviogarcia.androideventskotlin.model

import android.os.Parcel
import android.os.Parcelable

class Usuario(
        val _id: String,
        val nome: String,
        val sobrenome: String,
        val email: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(nome)
        parcel.writeString(sobrenome)
        parcel.writeString(email)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario = Usuario(parcel)
        override fun newArray(size: Int): Array<Usuario> = newArray(size)
    }
}
