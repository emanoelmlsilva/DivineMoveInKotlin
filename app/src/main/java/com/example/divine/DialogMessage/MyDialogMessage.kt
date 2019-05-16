package com.example.divine.DialogMessage

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class MyDialogMessage(myContext: Context, idStyle: Int) {

    var builder: AlertDialog.Builder

    init{
        builder = AlertDialog.Builder(myContext,idStyle)
    }

    fun message(title: String,back: BackToMenu){

        builder.setTitle(title)
//        configurar cancelamento
        builder.setCancelable(false)
//        configurar icone
        builder.setIcon(android.R.drawable.ic_dialog_info)
//        configurar opções de butões sim ou não
        builder.setPositiveButton("ok",DialogInterface.OnClickListener { dialog, which -> back.popBack() })

        builder.create()
        builder.show()

    }

    fun messageCheckVictor(title: String,id: Int){
        builder.setTitle(title)
//        configurar cancelamento
        builder.setCancelable(true)
//        configurar icone
        builder.setIcon(id)

        builder.create()
        builder.show()
    }
    interface BackToMenu{
        fun popBack()
    }
}