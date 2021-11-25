package com.tptz.playground

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner

@SuppressLint("StaticFieldLeak")
class MainApp : Application(), LifecycleObserver {
    override fun onCreate() {
        super<Application>.onCreate()
        context = applicationContext

        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() {
        Toast.makeText(this, "    ^_^", Toast.LENGTH_SHORT).show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
        Toast.makeText(this, "    v_v", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private var context: Context? = null

        @JvmStatic
        fun getContext(): Context = context!!
    }
}