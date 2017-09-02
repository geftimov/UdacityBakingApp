package com.eftimoff.bakingapp.app.models

import android.os.Parcel
import android.os.Parcelable

data class Ingredient(val quantity: Double, val measure: String, val ingredient: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(quantity)
        parcel.writeString(measure)
        parcel.writeString(ingredient)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ingredient> {
        override fun createFromParcel(parcel: Parcel): Ingredient {
            return Ingredient(parcel)
        }

        override fun newArray(size: Int): Array<Ingredient?> {
            return arrayOfNulls(size)
        }
    }

}
