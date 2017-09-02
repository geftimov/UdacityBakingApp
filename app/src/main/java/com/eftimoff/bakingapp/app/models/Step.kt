package com.eftimoff.bakingapp.app.models

import android.os.Parcel
import android.os.Parcelable

data class Step(val id: Long, val shortDescription: String, val description: String, val videoURL: String, val thumbnailURL: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(shortDescription)
        parcel.writeString(description)
        parcel.writeString(videoURL)
        parcel.writeString(thumbnailURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Step> {
        override fun createFromParcel(parcel: Parcel): Step {
            return Step(parcel)
        }

        override fun newArray(size: Int): Array<Step?> {
            return arrayOfNulls(size)
        }
    }

}