package com.example.reservebossclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_call.*

class CallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)


        var Callist = arrayOf("1번 테이블 : 포크 3개 추가", "2번 테이블 : 짜장면 3그릇, 짬뽕 2그릇",
                                "3번 테이블 : 짜장면 2그릇, 볶음밥 3그릇","4번테이블 : 콜라 2캔")
        var list : ListView = findViewById(R.id.CalllistView)
        var adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1,Callist)
        list.adapter = adapter

    }
}
