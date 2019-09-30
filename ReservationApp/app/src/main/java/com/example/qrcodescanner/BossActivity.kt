package com.example.reservebossclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.content.Intent
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView

class BossActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boss)

        var waitlist = arrayOf("대기자 순번 1번 , 손현오 , 010-6615-4583","대기자 순번 2번 , 윤상진, 010-4578-7894",
                                "대기자 순번 3번 , 손현오 , 010-6615-4583","대기자 순번 4번 , 윤상진, 010-4578-7894",
            "대기자 순번 5번 , 손현오 , 010-6615-4583","대기자 순번 6번 , 윤상진, 010-4578-7894",
            "대기자 순번 7번 , 손현오 , 010-6615-4583","대기자 순번 8번 , 윤상진, 010-4578-7894",
            "대기자 순번 9번 , 손현오 , 010-6615-4583","대기자 순번 10번 , 윤상진, 010-4578-7894",
            "대기자 순번 11번 , 손현오 , 010-6615-4583","대기자 순번 12번 , 윤상진, 010-4578-7894")
        var list : ListView = findViewById(R.id.WaitlistView)
        var adapter =ArrayAdapter(this,android.R.layout.simple_list_item_1,waitlist)
        list.adapter = adapter
    }
}
