package com.example.divine.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import com.example.divine.R

class HomeFragment : Fragment() {

    var buttonFilmes: ImageButton? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_home, container, false)
        buttonFilmes = view.findViewById(R.id.btn_gameStart)
        openStart()
        return view
    }

    fun openStart(){
        buttonFilmes?.setOnClickListener(View.OnClickListener {
            var fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.frameChoose,FasesFragment())
            fragmentTransaction.commit()
        })
    }


}
