package com.example.divine.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.divine.Adapter.AdapterRecordes
import com.example.divine.Controller.RecordController
import com.example.divine.Model.Records

import com.example.divine.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val array = RecordController(this!!.activity!!).allOfRecords()
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        var list = view.findViewById<ListView>(R.id.listView)

        var adapters = AdapterRecordes(array, this!!.activity!!)
        list.setAdapter(adapters)
        return view
    }

}
