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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch {
            val numbers = produceNumber()
            val squares = squares(numbers)

            repeat(6) {
                Log.d("Tatsuya", "onCreate: ${squares.receive()}")
            }
            Log.d("Tatsuya", "onCreate: done")
            coroutineContext.cancelChildren()
        }

    }
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.produceNumber(): ReceiveChannel<Int> = produce {
    var x = 1
    while (true) send(x++)
}

@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.squares(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}

