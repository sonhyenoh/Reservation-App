package com.example.qreservationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)            // 실제 사용할 메인 액티비티
        startActivity(intent)
        finish()
    }
}

