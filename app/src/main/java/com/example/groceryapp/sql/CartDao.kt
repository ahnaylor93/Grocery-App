package com.example.groceryapp.sql

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.groceryapp.data.CartItem
import com.example.groceryapp.data.Product
import java.sql.SQLException

class CartDao(val context: Context) {
    val db: SQLiteDatabase
    init {
        db = DBHelper(context).writableDatabase
    }

    fun getItems(): ArrayList<CartItem>{
        try {
            val itemsList = ArrayList<CartItem>()

            val cursor: Cursor = db.query("cart", null, null, null,
                null, null, null)
            while(cursor.moveToNext()){
                val itemId = cursor.getLong(0)
                val productId = cursor.getString(1)
                val productName = cursor.getString(2)
                val image = cursor.getString(3)
                val quantity = cursor.getInt(4)
                val price = cursor.getDouble(5)

                val cartItem = CartItem(itemId, productId, productName, image, quantity, price)
                itemsList.add(cartItem)
            }
            return itemsList
        }catch(e: SQLException){
            e.printStackTrace()
            return ArrayList<CartItem>()
        }
    }

    fun addItem(cartItem: CartItem): Boolean {
        try{
            val values: ContentValues = ContentValues()
            values.put("productId", cartItem.productId)
            values.put("productName", cartItem.productName)
            values.put("image", cartItem.image)
            values.put("quantity", cartItem.quantity)
            values.put("price", cartItem.price)
            val id:Long = db.insert("cart", null, values)
            return id != -1L
        }catch(e: SQLException){
            e.printStackTrace()
            return false
        }
    }

    fun deleteItem(itemId: Long): Boolean {
        val numberRowsDeleted = db.delete("cart", "itemId = $itemId", null)

        return numberRowsDeleted == 1
    }

    fun updateItem(cartItem: CartItem): Boolean {
        val values = ContentValues()

        values.put("itemId", cartItem.itemId)
        values.put("productId", cartItem.productId)
        values.put("productName", cartItem.productName)
        values.put("image", cartItem.image)
        values.put("quantity", cartItem.quantity)
        values.put("price", cartItem.price)
        val numberOfRowsUpdated = db.update("cart", values,"itemId=${cartItem.itemId}", null)
        return numberOfRowsUpdated == 1
    }

    fun hasItem(product: Product): Boolean {
        var cursor = db.rawQuery("""
            SELECT COUNT(itemId)
            FROM cart
            WHERE '${product._id}' LIKE productId
        """.trimIndent(), null)

        cursor.moveToNext()
        val numberFound = cursor.getInt(0)
        return numberFound == 1
    }
}