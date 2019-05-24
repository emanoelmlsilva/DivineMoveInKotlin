package com.example.divine.Chronometer

import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Chronometer

class MyChronometer(viewChronometer: View) {

    var cronometro: Chronometer;
    var timer: Long = 0
    val TIME_FINAL = 45000
    var timeFinish: Boolean = false

    init{
            cronometro = viewChronometer as Chronometer
    }


//   Começa a contagem do cronômetro
    fun startChronometer(){
        timer = SystemClock.elapsedRealtime()
        cronometro.base = SystemClock.elapsedRealtime()
        cronometro.start()
    }

//   Para o cronômetro e retorna o tempo
    fun stopAndResetChronometer(){
        cronometro.stop()
    }

//    voltando do stop
    fun backFromStartChronometer(){
        cronometro.start()
    }

    //
    fun finishTime(timeEnd:TimeOver){
        cronometro.setOnChronometerTickListener {
            timer = SystemClock.elapsedRealtime() - cronometro.base
            timeFinish = timer >= TIME_FINAL
            if(timeFinish){
                timeEnd.finishOver()
            }
        }
    }

    fun resetTime(){
        stopAndResetChronometer()
        startChronometer()
    }

    fun getTimefinal(): Int{
        return (SystemClock.elapsedRealtime() - cronometro.base).toInt()
    }

    interface TimeOver{
        fun finishOver()
    }

}