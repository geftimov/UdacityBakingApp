package com.eftimoff.bakingapp.app.models

import android.os.Parcel
import android.os.Parcelable

data class Recipe(val id: Long, val name: String, val ingredients: List<Ingredient>, val steps: List<Step>, val servings: Long, val image: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.createTypedArrayList(Ingredient),
            parcel.createTypedArrayList(Step),
            parcel.readLong(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeTypedList(ingredients)
        parcel.writeTypedList(steps)
        parcel.writeLong(servings)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }

}


