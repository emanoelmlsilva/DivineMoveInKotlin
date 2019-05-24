package com.example.divine.Controller

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.divine.DataBase.DataBase
import com.example.divine.Model.Records

class RecordController(context: Context) : DataBase(context) {

    companion object{
        var nameTable: String = "fase1"
    }

    init {
        Log.i("criando tabela","criada")
        createTable()
    }

    override fun setNameTable(table: String){
        nameTable = table
    }

    fun getName(): String{
        return nameTable
    }

    override fun createContentValues(record: Records): ContentValues {

        val contentValues: ContentValues = ContentValues()

        contentValues.put("nick",record.nick)
        contentValues.put("score",record.score)
        contentValues.put("hit",record.totalHit)
        contentValues.put("totalImage",record.totalImage)
        return contentValues
    }

    override fun getTable(): String {
        return nameTable

    }

    override fun createRegisterRecords(cursor: Cursor): Records {

        val id: Int = cursor.getColumnIndex("id")
        val nick: String = cursor.getString(cursor.getColumnIndex("nick"))
        val score: Int = cursor.getInt(cursor.getColumnIndex("score"))
        val hit: Int = cursor.getInt(cursor.getColumnIndex("hit"))
        val totalImage: Int = cursor.getInt(cursor.getColumnIndex("totalImage"))

        val record: Records = Records(id,nick,score,hit,totalImage)

        return record
    }

    override fun getUpdate(record: Records): String {
        return "nick = '${record.nick}', score = '${record.score}', hit = '${record.totalHit}',  totalImage = '${record.totalImage}'"
    }

}