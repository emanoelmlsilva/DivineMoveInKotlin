package com.example.divine.Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.divine.Activity.PlayActivity
import com.example.divine.ArchiveRecover.Reader

import com.example.divine.R
import kotlinx.android.synthetic.main.fragment_fases.*

class FasesFragment : Fragment(),View.OnClickListener {

    var fase1: ImageButton? = null
    var fase2: ImageButton? = null
    var fase3: ImageButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_fases, container, false)
        fase1 = view?.findViewById(R.id.btn_fase1)
        fase2 = view?.findViewById(R.id.btn_fase2)
        fase3 = view?.findViewById(R.id.btn_fase3)
        startGameOfPhase()
        return view
    }

    fun startGameOfPhase(){
        clickButton(this!!.fase1!!)
        clickButton(this!!.fase2!!)
        clickButton(this!!.fase3!!)

    }

    fun escolherFases(title: String){
            Reader().setFile(title)
            val intentObject = Intent(activity,PlayActivity::class.java)
            startActivity(intentObject)
    }

    fun clickButton(button: ImageButton){
        button.setOnClickListener(this)
    }

    override fun onClick(view: View){

        when(view as ImageButton){

            btn_fase1 -> escolherFases("fase1")
            btn_fase2 -> escolherFases("fase2")
            btn_fase3 -> escolherFases("fase3")
        }
    }

}
