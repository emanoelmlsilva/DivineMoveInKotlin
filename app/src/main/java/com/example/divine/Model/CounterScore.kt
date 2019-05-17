package com.example.divine.Model

import android.util.Log

class CounterScore(var level: Int) {

    var contPartial: Int

    init{
        contPartial = 0
    }
    fun takeScore(): Int{
        when(level){
            1 -> return 3
            2 -> return 5
            3 -> return 7
            else -> return 0
        }
    }

    fun calculateScore(temp: Int){
        contPartial += takeScore() + (temp/3000)
    }

    fun getScoreFinal():Int{
        return contPartial
    }

    fun changeLevel(newLevel: Int){
        this.level = newLevel
    }
}