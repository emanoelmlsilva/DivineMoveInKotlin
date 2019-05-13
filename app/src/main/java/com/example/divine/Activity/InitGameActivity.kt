package com.example.divine.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.divine.Model.ChoseWord
import com.example.divine.R
import kotlinx.android.synthetic.main.activity_main.*

class InitGameActivity : AppCompatActivity() {

    var choseWord: ChoseWord? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        choseWord = ChoseWord(this)
        btn_films.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)
        })

    }
}
