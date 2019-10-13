package com.example.reservationapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.Adapter
import android.content.Intent
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_confirm.*

 class ConfirmActivity() : AppCompatActivity() {
     lateinit var number: String
     var confirmnumber :String?= null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)
        number = intent.extras?.get("Result").toString()
        if(number!= "null") {
            confirmnumber = number
            number_value.text = confirmnumber
            number_before.text = confirmnumber
            number_time.text =confirmnumber + "분"
        }



        // val text = findViewById<TextView>(R.id.number_value)   //그냥 number_value.setText("")해도 되지만 findViewById를 이용해서 바꿔보았다
            //만약 id를 직빵으로 이용하고 싶으면 import kotlinx.android.synthetic.main.activity_confirm.*해주면 된다 즉 시험에서 setText이용할때 위에 import 적어주기힘드니깐 findViewById이용하기
    }

     override fun onBackPressed() {

         if(confirmnumber != null ) {
             val resultinput = Intent(this, MainActivity()::class.java)
             resultinput.putExtra("ResultBack",confirmnumber)
             startActivityForResult(resultinput,1)
             finish()
         }else{
             val intentjust = Intent(this,MainActivity::class.java)
             startActivity(intentjust)
             finish()  //아직 qrscan하지 않은 상태에서 confirm들어왔다가 다시 나가면 그냥 앱이 꺼지기때문에 back버튼 누를때 data가 없더라도 다시 mainactivity로 가게 만들었다

         }
         super.onBackPressed()


     }


}
