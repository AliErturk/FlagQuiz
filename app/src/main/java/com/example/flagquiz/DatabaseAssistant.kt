package com.example.flagquiz

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseAssistant(context: Context) : SQLiteOpenHelper(context,"flags.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS \"flags\" (\n" +
                "\t\"flag_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t\"flag_name\"\tTEXT,\n" +
                "\t\"flag_picture\"\tTEXT\n" +

                ")")

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS flags ")
        onCreate(db)

    }
}