package com.example.groceryapp.sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(val context: Context): SQLiteOpenHelper(context, "CartDB", null, 1) {
    companion object {
        const val CREATE_CART_TABLE_QUERY = """
            CREATE TABLE cart(
            itemId INTEGER PRIMARY KEY AUTOINCREMENT,
            productId TEXT,
            productName TEXT,
            image TEXT,
            quantity INTEGER,
            price DOUBLE)"""
    }
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(CREATE_CART_TABLE_QUERY)
        }catch(e: SQLiteException){
            e.printStackTrace()
            Toast.makeText(context,"ERROR CREATING TABLE: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

}