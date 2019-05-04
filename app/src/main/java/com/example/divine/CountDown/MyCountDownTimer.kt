package com.example.divine.CountDown

import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.os.CountDownTimer
import android.widget.TextView
import androidx.annotation.RequiresApi

class MyCountDownTimer(mContext: Context, mTextView: TextView, millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {

    var myContext: Context
    var myTextView: TextView
    var timeInFuture: Long = 0

    init {
        myContext = mContext
        myTextView = mTextView
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onFinish() {

        timeInFuture -= 1000
        myTextView.setText(getCorretcTimer(true,timeInFuture)+":"+getCorretcTimer(false,timeInFuture))

    }

    override fun onTick(millisUntilFinished: Long) {
        timeInFuture = millisUntilFinished
        myTextView.setText(getCorretcTimer(true,millisUntilFinished)+":"+getCorretcTimer(false,millisUntilFinished))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getCorretcTimer(isMinute: Boolean, millisUntilFinished: Long): String{

        var constCalendar: Int
            if(isMinute){
              constCalendar = Calendar.MINUTE
            }else{
                constCalendar = Calendar.SECOND
            }
        var calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = millisUntilFinished

        if(calendar.get(constCalendar) < 10){
            return "0"+calendar.get(constCalendar)
        }else{
            return ""+calendar.get(constCalendar)
        }
    }
}