package com.example.divine.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.divine.Model.Records

val DATABASE_VERSION: Int = 1
val DATABASE_NAME: String = "guess.DB"

abstract class DataBase(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val sql: String = "DROP TABLE ${getTable()}"
        db?.execSQL(sql)
        onCreate(db)

    }

    fun createTable(){
        val stringCreate: String = "CREATE TABLE IF NOT EXISTS ${getTable()} ( ${getCreateTableDescription()} ) "
        executeSql(stringCreate)
    }

    fun executeSql(SQL: String){
        val db: SQLiteDatabase? = writableDatabase
        db?.execSQL(SQL)
        db?.close()
    }

    fun addRecord(record: Records?){
        val db: SQLiteDatabase? = writableDatabase
        val contentValues: ContentValues = createContentValues(record!!)
        db?.insert(getTable(),null,contentValues)
        db?.close()
    }

    fun allOfRecords(): ArrayList<Records>{

        val listRecords = ArrayList<Records>()

        val sql: String = "SELECT * FROM ${getTable()}"

        val db: SQLiteDatabase? = readableDatabase

        val cursor: Cursor = db!!.rawQuery(sql,null)

        if(cursor.moveToFirst()){
            do{
                listRecords.add(createRegisterRecords(cursor))
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return listRecords // adicionar metodo de ordenação
    }

    fun updateRecord(record: Records){
        val sql: String = "UPDATE ${getTable()} SET ${getUpdate(record)}"
        val db: SQLiteDatabase? = writableDatabase
        db?.execSQL(sql)
        db?.close()
    }

    fun delete() {

        val sql = "DROP TABLE ${getTable()}"
        val db = writableDatabase
        db.execSQL(sql)
        db.close()


    }

    fun getCreateTableDescription(): String{
        return "id INTEGER PRIMARY KEY AUTOINCREMENT, nick varchar(20) NOT NULL, score int NOT NULL, hit int NOT NULL, totalImage int NOT NULL"
    }

    abstract fun createContentValues(record: Records): ContentValues

    abstract fun getTable():String

    abstract fun createRegisterRecords(cursor: Cursor): Records

    abstract fun getUpdate(record: Records): String

    abstract fun setNameTable(table: String)

}