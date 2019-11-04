package com.example.qreservationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //lateinit var confirm_login : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            bt_reservation.setOnClickListener{

            val intent = Intent(this, ReservationActivity::class.java)
            startActivity(intent)

        }
            bt_login.setOnClickListener{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

}
