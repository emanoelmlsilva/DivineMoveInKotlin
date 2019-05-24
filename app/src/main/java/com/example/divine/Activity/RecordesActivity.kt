package com.example.divine.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.divine.Controller.RecordController
import com.example.divine.DataBase.DataBase
import com.example.divine.Fragments.ListFragment
import com.example.divine.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recordes.*

class RecordesActivity : AppCompatActivity(), View.OnClickListener {

    var dataBaseFase: DataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordes)

        dataBaseFase = RecordController(this)

        initialization()
    }

    fun initialization(){

        clickButtons(btn_recFase1)
        clickButtons(btn_recFase2)
        clickButtons(btn_recFase3)
    }

    fun clickButtons(button: Button){

        button.setOnClickListener(this)

    }

    override fun onClick(view: View){

        when(view){

            btn_recFase1 -> dataBaseFase?.setNameTable("faseEasy")
            btn_recFase2 -> dataBaseFase?.setNameTable("faseMedium")
            btn_recFase3 -> dataBaseFase?.setNameTable("faseHard")

        }

        if(dataBaseFase?.allOfRecords()?.size == 0){

            Snackbar.make(view,"Não ha resutado de pontos",Snackbar.LENGTH_SHORT).show()

        }else{
            chooseFragmentv(ListFragment())
        }
    }

    fun chooseFragmentv(chosen: Fragment){

        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.recordeConstrint, chosen)
        fragmentTransaction.commit()

    }

}
