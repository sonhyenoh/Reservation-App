package com.example.reservebossclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.ArrayAdapter
class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        var Setting = arrayOf("테이블 갯수 : 5개","아이디/비번 수정","Call할때 주문 받을 메뉴들 : 짜장면, 짬뽕, 볶음밥, 콜라, 사이다, 접시, 포크, 젓가락, 물컵")
        var list : ListView = findViewById(R.id.SettinglistView)
        var adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1,Setting)
        list.adapter = adapter
    }
}
