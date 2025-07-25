package com.example.catatanharian

import android.adservices.adid.AdId
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CatatanDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "catatanapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allcatatan"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }


    fun insertCatatan(catatan: Catatan){
         val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, catatan.title)
            put(COLUMN_CONTENT, catatan.content)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun getAllCatatan(): List<Catatan> {
            val catatanList = mutableListOf<Catatan>()
        val  db = readableDatabase
        val  query = "SELECT * FROM $TABLE_NAME"
        val  cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val catatan = Catatan(id, title, content)
            catatanList.add(catatan)
        }

        cursor.close()
        db.close()
        return catatanList
    }

//       update
        fun  updateCatatan(catatan: Catatan) {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_TITLE, catatan.title)
                put(COLUMN_CONTENT, catatan.content)
            }
            val whereClause = "$COLUMN_ID = ?"
            val whereArgs = arrayOf(catatan.id.toString())
            db.update(TABLE_NAME, values, whereClause, whereArgs)
            db.close()

        }

         fun  getCatatanByID(catatanId: Int): Catatan {
             val db = readableDatabase
             val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $catatanId"
             val cursor = db.rawQuery(query, null)
             cursor.moveToFirst()

             val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
             val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
             val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

             cursor.close()
             db.close()
             return Catatan(id, title, content)
         }
//    end update

//    untuk delete
            fun  deleteCatatan(catatanId: Int) {
            val db = writableDatabase
            val whereClause = "$COLUMN_ID = ?"
            val whereArgs = arrayOf(catatanId.toString())
            db.delete(TABLE_NAME, whereClause, whereArgs)
            db.close()
    }
}