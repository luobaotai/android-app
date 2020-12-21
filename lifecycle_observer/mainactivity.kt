package com.example.lifecycledemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(LifeObserver())
    }

}

class LifeObserver: LifecycleObserver{
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun on_create(){
        Log.d("luobaotai", "on create")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun on_pause(){
        Log.d("luobaotai", "on pause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun on_stop(){
        Log.d("luobaotai", "on stop")
    }
}
