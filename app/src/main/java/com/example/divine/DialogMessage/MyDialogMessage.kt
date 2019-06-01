package com.example.divine.DialogMessage

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.widget.Chronometer
import androidx.appcompat.app.AlertDialog
import com.example.divine.Chronometer.MyChronometer
import com.example.divine.R

class MyDialogMessage(myContext: Context, idStyle: Int) {

    var builder: AlertDialog.Builder

    init{
        builder = AlertDialog.Builder(myContext,idStyle)
    }

    fun message(title: String,back: BackToMenu){
        Log.i("mensagem","escohida o normal")
        builder.setTitle(title)
//        configurar cancelamento
        builder.setCancelable(false)
//        configurar icone
        builder.setIcon(android.R.drawable.ic_dialog_info)
//        configurar opções de butões sim ou não
        builder.setPositiveButton("Ok",DialogInterface.OnClickListener { dialog, which -> back.popBack() })
        builder.setNegativeButton("",DialogInterface.OnClickListener { dialog, whick -> null})
        builder.create()
        builder.show()

    }

    fun messageCheckExit(title: String,back: BackToMenu){
        Log.i("mensagem","escohida o exit")
        builder.setTitle(title)
//        configurar cancelamento
        builder.setCancelable(false)
//        configurar icone
        builder.setIcon(R.drawable.alert_box)
//configurar opção de sim ou não
        builder.setPositiveButton("Ok",DialogInterface.OnClickListener {dialog, which -> back.popBack() })
        builder.setNegativeButton("Cancel",DialogInterface.OnClickListener{dialog, which -> })

        builder.create()
        builder.show()
    }

    interface BackToMenu{
        fun popBack()
    }
}