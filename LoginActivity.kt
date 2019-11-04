package com.example.qrcodereservationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_loginActLogin.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("Id","ok7624583")
            startActivityForResult(intent,1)
            finish()
        }
    }
}
