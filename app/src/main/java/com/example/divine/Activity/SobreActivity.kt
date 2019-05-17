package com.example.divine.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.divine.R
import mehdi.sakout.aboutpage.AboutPage

class SobreActivity : AppCompatActivity() {

//    não utiliza o layout activity_sobre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val description = "Um projeto para desenvolver as habilidade e os conhecimentos aprendido ao decorre dos estudos com kotlin.\n\n"+
"Nome_do_Jogo ele serve para instigar a capacidade motora e reflexiva do jogador ao desafiá-lo a descobrir quais imagens são referentes a quais filmes em pouco tempo,"+
"com a dificuldade de um teclado embaralhado. Tem o intuito de ser um jogo divertido e instigante."
        val sobre: View = AboutPage(this)
            .setImage(R.drawable.designhome3)
            .setDescription(description)
            .addGroup("Fale Conosco")
            .addEmail("emanoel.silva@dce.ufpb.br","Envie um e-mail")
            .addGroup("Outros Projetos")
            .addGitHub("emanoelmlsilva?tab=repositories","GitHub")
            .create()
        setContentView(sobre)
    }
}
