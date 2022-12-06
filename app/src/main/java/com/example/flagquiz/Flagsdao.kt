package com.example.flagquiz

class Flagsdao {

    fun getRandom5Flags(da : DatabaseAssistant ) : ArrayList<Flags> {

        val flagList = ArrayList<Flags>()

        val db = da.writableDatabase

        val cursor = db.rawQuery("SELECT*FROM flags ORDER BY RANDOM() LIMIT 5", null)

        while (cursor.moveToNext()){
            val flag = Flags(
                cursor.getInt(cursor.getColumnIndexOrThrow("flag_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("flag_name")),
                cursor.getString(cursor.getColumnIndexOrThrow("flag_picture"))
            )
            flagList.add(flag)
        }
        return flagList
    }
    fun getRandom3FalseFlags(da : DatabaseAssistant ,flag_id :Int ) : ArrayList<Flags> {

        val flagList = ArrayList<Flags>()

        val db = da.writableDatabase

        val cursor = db.rawQuery("SELECT*FROM flags WHERE flag_id !=$flag_id ORDER BY RANDOM() LIMIT 3", null)

        while (cursor.moveToNext()){
            val flag = Flags(
                cursor.getInt(cursor.getColumnIndexOrThrow("flag_id")),
                cursor.getString(cursor.getColumnIndexOrThrow("flag_name")),
                cursor.getString(cursor.getColumnIndexOrThrow("flag_picture"))
            )
            flagList.add(flag)
        }
        return flagList
    }



}