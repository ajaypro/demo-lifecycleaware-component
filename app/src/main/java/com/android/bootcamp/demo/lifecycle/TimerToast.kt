package com.android.bootcamp.demo.lifecycle

import android.app.Application
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent


/**
 * Making the timer toast class lifecycle aware by passing lifecycleonwer and implementing lifecyclerobserver
 * the lifecycleobserver will observe the changes in this class(i.e whenever timer changes) and in the mainactivity
 *
 */
class TimerToast(context: Application, lifecycleOwner: LifecycleOwner) : LifecycleObserver {

    private var started = false
    private val lifecycle = lifecycleOwner.lifecycle

    init {
        lifecycle.addObserver(this) // This observs the changes of timer toast which is been updated in mainactivity
    }

    private val timer = object : CountDownTimer(60000, 3000) {


        override fun onFinish() {

            // this method would be triggered based on lifecycle state of activity destroyed
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.DESTROYED))
                Toast.makeText(context, "Finish", Toast.LENGTH_SHORT).show()
        }

        override fun onTick(millisUntilFinished: Long) {

            // this method would be triggered based on lifecycle state of activity started
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                Toast.makeText(context, "$millisUntilFinished", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // check for lifecycle event started of lifecycleowner
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (!started) {
            started = true
            timer.start()
        }
    }

    // check for lifecycle event destroyed of lifecycleowner
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop() {
        timer.cancel()
    }
}