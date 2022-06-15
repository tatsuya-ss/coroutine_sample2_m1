package com.example.coroutine_sample2_m1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch {
            Log.d("Tatsuya🐲", "fetch前: ${this.coroutineContext}")
            val result = fetch()
            Log.d("Tatsuya🐲", "fetch後: ${this.coroutineContext}")
            Log.d("Tatsuya🐲", "onCreate: ${result}")
        }
    }

    private suspend fun fetch(): String {
        return withContext(Dispatchers.IO) {
            Log.d("Tatsuya🐲", "fetch内部: ${this.coroutineContext}")
            delay(1000L)
            "⚽️"
        }
    }
}