package com.example.groceryapp.data

import android.os.Parcel
import android.os.Parcelable

data class CartItem(
    val itemId: Long,
    val productId: String,
    val productName: String,
    val image: String,
    var quantity: Int,
    val price: Double
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readString()?: "",
        parcel.readInt()?: 0,
        parcel.readDouble()?: 0.0
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(itemId)
        parcel.writeString(productId)
        parcel.writeString(productName)
        parcel.writeString(image)
        parcel.writeInt(quantity)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CartItem> {
        override fun createFromParcel(parcel: Parcel): CartItem {
            return CartItem(parcel)
        }

        override fun newArray(size: Int): Array<CartItem?> {
            return arrayOfNulls(size)
        }
    }
}