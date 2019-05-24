package com.example.divine.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.divine.ArchiveRecover.Reader
import com.example.divine.Controller.RecordController
import com.example.divine.DataBase.DataBase
import com.example.divine.R
import kotlinx.android.synthetic.main.activity_fases.*

class FasesActivity : AppCompatActivity(), View.OnClickListener{

    var faseDB: DataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fases)
        faseDB = RecordController(this)
        startGameOfPhase()
    }

    fun startGameOfPhase(){
        clickButton(btn_fase1)
        clickButton(btn_fase2)
        clickButton(btn_fase3)

    }

    fun escolherFases(title: String){
        Reader().setFile(title)
        val intentObject = Intent(this,PlayActivity::class.java)
        startActivity(intentObject)
    }

    fun clickButton(button: Button){
        button.setOnClickListener(this)
    }

    override fun onClick(view: View){

        when(view){

            btn_fase1 -> {
                faseDB?.setNameTable("faseEasy")
                escolherFases("fase1")
            }
            btn_fase2 -> {
                faseDB?.setNameTable("faseMedium")
                escolherFases("fase2")
            }
            btn_fase3 -> {
                faseDB?.setNameTable("faseHard")
                escolherFases("fase3")
            }
        }
    }

}
