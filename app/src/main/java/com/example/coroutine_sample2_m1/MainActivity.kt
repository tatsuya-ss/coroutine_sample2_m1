package com.example.coroutine_sample2_m1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val channel = Channel<Int>()

        scope.launch {
            launch {
                for (x in 1..5) {
                    channel.send(x * x)
                }
            }
            repeat(5) { Log.d("Tatsuya🐲", "onCreate: ${channel.receive()}") }
            Log.d("Tatsuya🐲", "onCreate: Done")
        }

    }
}