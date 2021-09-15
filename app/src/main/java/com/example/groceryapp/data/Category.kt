package com.example.groceryapp.data

import android.os.Parcel
import android.os.Parcelable

data class Category(
    val catId: Int,
    val catImage: String,
    val catDescription: String,
    val catName: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt()?: -1,
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(catId)
        parcel.writeString(catImage)
        parcel.writeString(catDescription)
        parcel.writeString(catName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}