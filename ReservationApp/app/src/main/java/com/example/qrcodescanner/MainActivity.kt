package com.example.reservebossclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        /*login.setOnClickListtener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }*/

        Waitlistbutton.setOnClickListener{
            val intent = Intent(this,BossActivity::class.java)
            startActivity(intent)//다음화면으로 넘어가기
            }
        Callbutton.setOnClickListener{
            val intent =  Intent(this,CallActivity::class.java)
            startActivity(intent)
        }
        Settingbutton.setOnClickListener{
            val intent = Intent(this,SettingActivity::class.java)
            startActivity(intent)
        }
        }


    }


