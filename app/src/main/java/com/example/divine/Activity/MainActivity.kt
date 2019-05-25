package com.example.divine.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.divine.Controller.RecordController
import com.example.divine.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionClick()
        RecordController(this).setNameTable("faseEasy")
        RecordController(this).setNameTable("faseMedium")
        RecordController(this).setNameTable("faseHard")
    }

    fun actionClick(){
        btn_jogar.setOnClickListener(this)
        btn_ajudar.setOnClickListener(this)
        btn_recordes.setOnClickListener(this)
    }

    override fun onClick(view: View){
        openActivity(view as Button)
    }

    fun openActivity(button: Button){

        when(button){
            btn_jogar -> {startActivity(Intent(this,FasesActivity::class.java))}
            btn_ajudar -> {startActivity(Intent(this,SobreActivity::class.java))}
            btn_recordes -> {startActivity(Intent(this,RecordesActivity::class.java))}
        }

    }
}
