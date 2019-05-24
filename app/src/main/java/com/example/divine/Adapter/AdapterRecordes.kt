package com.example.divine.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.divine.Model.Records
import com.example.divine.R

class AdapterRecordes(val listRecords: ArrayList<Records>, val myActivity: Activity): BaseAdapter() {

    var nick: TextView? = null
    var totalImage: TextView? = null
    var totalHit: TextView? = null
    var score: TextView? = null


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = myActivity.layoutInflater.inflate(R.layout.lista_recordes,parent,false)
        nick = view.findViewById(R.id.textNick)
        totalImage = view.findViewById(R.id.textTotalImage)
        totalHit = view.findViewById(R.id.textTotalHit)
        score = view.findViewById(R.id.textScore)

        val record: Records = listRecords.get(position)
        fillView(record)

        return view
    }

    override fun getItem(position: Int): Any {
        return this.listRecords.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return this.listRecords.size
    }

    @SuppressLint("SetTextI18n")
    fun fillView(record: Records){
        nick?.text = "nick: ${record.nick}"
        totalImage?.text = "Total de Imagem: ${record.totalImage}"
        totalHit?.text = "Total de Acertos: ${record.totalHit}"
        score?.text = "Pontos: ${record.score}"
    }
}