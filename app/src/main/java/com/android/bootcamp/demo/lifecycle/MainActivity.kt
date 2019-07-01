package com.android.bootcamp.demo.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var timerToast: TimerToast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * TimerToast instance takes context and lifecycleonwer(mainactivity) and when
         * activity starts - timer starts
         * activity stops - timer stops based on the lifecyclevents of the activity been observed by TimerToast
         * component which is made as lifecycleaware
         */

        timerToast = TimerToast(application, this)
    }

    override fun onStart() {
        super.onStart()
        timerToast.start() // Starting the timer
    }

}
