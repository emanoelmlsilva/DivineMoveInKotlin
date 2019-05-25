package com.example.divine.Activity

import android.app.ProgressDialog.show
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.divine.Controller.RecordController
import com.example.divine.DataBase.DataBase
import com.example.divine.Fragments.ListFragment
import com.example.divine.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
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

            snackbarCustom(view)

        }else{
            chooseFragmentv(ListFragment())
        }
    }

    fun chooseFragmentv(chosen: Fragment){

        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.recordeConstrint, chosen)
        fragmentTransaction.commit()

    }

    private fun snackbarCustom(view:View){
        val snackbar: Snackbar = make(view,"Não ha resutado",Snackbar.LENGTH_SHORT)
        val snackView: View = snackbar.view
        snackView.setBackgroundColor(Color.parseColor("#E5FFFF"))
        val textview: TextView = snackView.findViewById(R.id.snackbar_text)
        textview.setTextColor(Color.parseColor("#212121"))
        snackbar.show()
    }

}
