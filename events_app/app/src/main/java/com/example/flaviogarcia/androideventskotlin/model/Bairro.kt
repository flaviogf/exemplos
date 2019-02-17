package com.example.flaviogarcia.androideventskotlin.model

import android.os.Parcel
import android.os.Parcelable

class Bairro(
        val _id: String,
        val nome: String) : Parcelable {

    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(nome)
    }

    override fun describeContents() = 0

    override fun toString() = nome

    companion object CREATOR : Parcelable.Creator<Bairro> {
        override fun createFromParcel(parcel: Parcel): Bairro = Bairro(parcel)
        override fun newArray(size: Int): Array<Bairro> = newArray(size)
    }
}
