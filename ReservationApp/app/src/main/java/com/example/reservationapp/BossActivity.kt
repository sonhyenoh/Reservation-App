package com.example.reservationapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_boss.*

class BossActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boss)

        button_waitListActivity.setOnClickListener{
            val intent = Intent(this, WaitListActivity::class.java)
            startActivity(intent)//다음화면으로 넘어가기
        }

        button_callActivity.setOnClickListener{
            val intent =  Intent(this, CallActivity::class.java)
            startActivity(intent)
        }

        button_settingActivity.setOnClickListener{
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}