package com.example.groceryapp.data

import android.os.Parcel
import android.os.Parcelable

data class SubCategory(
    val subImage:String,
    val subDescription:String,
    val subId:Int,
    val subName:String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readString()?:""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subImage)
        parcel.writeString(subDescription)
        parcel.writeInt(subId)
        parcel.writeString(subName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SubCategory> {
        override fun createFromParcel(parcel: Parcel): SubCategory {
            return SubCategory(parcel)
        }

        override fun newArray(size: Int): Array<SubCategory?> {
            return arrayOfNulls(size)
        }
    }
}
