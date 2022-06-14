package com.example.coroutine_sample2_m1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val job1 = scope.launch {
            Log.d("Tatsuya", "job1: ${coroutineContext}")
        }

        val job2 = scope.launch(Dispatchers.Default) {
            Log.d("Tatsuya", "job2: ${coroutineContext}")
        }

    }
}