package com.example.divine.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.divine.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    var buttonFilmes: ImageButton? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_home, container, false)
        openStart()
        return view
    }

    fun openStart(){
        btn_game.setOnClickListener(View.OnClickListener {
            var fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.frameChoose,FasesFragment())
            fragmentTransaction.commit()
        })
    }


}
