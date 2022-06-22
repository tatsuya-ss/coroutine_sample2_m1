package com.example.coroutine_sample2_m1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch {
            val channel = Channel<Int>(capacity = 4)
            val sender = launch {
                repeat(10) {
                    Log.d("Tatsuya", "${it}送信中")
                    channel.send(it)
                }
            }

            delay(1000)
            sender.cancel()
        }

    }
}
