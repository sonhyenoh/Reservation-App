package com.example.qrcodereservationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuInflater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var Id :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Id = intent.extras?.get("Id").toString()
        if(Id == "ok7624583") {
            text_user.text = Id
        }
        image_Qr.setOnClickListener{
            val intent = Intent(this,QReserveActivity::class.java)
            startActivity(intent)
        }
        image_call.setOnClickListener{
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_setting -> {

                true
            }
            R.id.action_login -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
        //로그인 하고나서 menu의 버튼이 로그아웃으로 바뀌고 싶다...
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
