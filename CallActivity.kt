package com.example.reservationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.reservationapp.DbStructure.ReservationInfo
import com.example.reservationapp.DbStructure.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_call.*
import kotlinx.android.synthetic.main.activity_call.cancle_reservation
import kotlinx.android.synthetic.main.activity_call.text_storename
import kotlinx.android.synthetic.main.activity_qr_reservation.*

class CallActivity : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val mAuth = FirebaseAuth.getInstance()
    var user = mAuth.currentUser
    // 나중에 얘는 order로 교체해야 됨
    var myRefUser = database.getReference("UserList")
    var userID = ""

    var CallArrayList = arrayListOf<Calllist>(
        Calllist("돈까스","6000","don"),
        Calllist("치즈 돈까스","7000","cheeze") ,
        Calllist("고구마 돈까스","7000","goguma"),
        Calllist("대왕 돈까스","8000","king")
    )
    var OptionArrayList = arrayListOf<OptionList>(
        //firebase에서 받았을때는 식당마다 받을꺼니깐 치킨집이면 순살 뼈, 이렇게 정해져있듯이 괜찮을듯
        //중국집이면 곱빼기이런거 추가하면 되듯이 optionlist는 가게마다 하나씩 적고 반영해도 ㄱㅊ
        //firebase에서 가져올때 되게 단순화 시키기 치킨집이면 순살,뼈라든지 짜장면집같은경우는 곱빼기 보통이라던지
        OptionList("매운맛",0),
        OptionList("보통맛",0),
        OptionList("순한맛",0)
    )
    val count : String  by lazy { intent.extras?.get("Count").toString()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        if(count == "null"){
            text_all.text = "0"
        }else {
            text_all.text = count.toString()
        }
        user = mAuth.currentUser
        if(user != null && user!!.email != null){
            userID = user!!.email!!
        }
        else{
            userID = "No User"
        }

        //firebase에서 하나씩 올때마다 차례차례 list에 집어넣기
        val list :ListView = findViewById(R.id.list_call)
        val adapter = ListAdapter(this, CallArrayList)
        list.adapter = adapter

        bt_pay.setOnClickListener {
            if( count =="null"){
                Toast.makeText(this, "주문 함에 아무것도 없습니다.", Toast.LENGTH_LONG).show()
            }else {
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra("price", count)
                startActivityForResult(intent, 1)
            }
        }
        list.setOnItemClickListener()
        {parent,itemView,position,id->
            val intent  = Intent(this, CallConfirmAcitivity::class.java)
            intent.putExtra("Menu", CallArrayList[position])
            intent.putExtra("list",OptionArrayList)
            intent.putExtra("totalcount",count)
            //그냥 적으면 CallArrayList가 그냥 보낼수있는 형식이 아니여서 error뜸 parceable만들어줘야함
            startActivityForResult(intent,1)
        }


        myRefUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                text_storename.text = dataSnapshot.child(removeAt(userID)).child("resName").getValue(String::class.java)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("zxcv", "Failed to read value.", error.toException())
            }
        })

        cancle_reservation.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        myRefUser.child("DataChangeEvent").setValue(0)
        myRefUser.child("DataChangeEvent").removeValue()
    }

/*   도저히 안된다 나중에 다시생각, callactivity에서 음식이름과 가격 중간에 옵션에 관해서 textview를 넣을려고 햇는데 잘안된다
  override fun toString(): String {
        val count = 0
        var foodarray = arrayOf("0")
        //callactivity에서 listview만들때 중간에 글자가 이상한게 찍혀서 toString재상속 받아야할듯
        while(count> OptionArrayList.size){

        }
        return foodarray
    }
    */

    // 이메일에서 @를 없애고 AT으로 교체, .을 없애고 DOT으로 교체
    fun removeAt(userID : String) : String{
        var returnString = ""
        for((index, value) in userID.withIndex()){
            if(value != '@' && value != '.'){
                returnString += value
            }
            else if(value == '.'){
                returnString += "DOT"
            }
            else{
                returnString += "AT"
            }
        }
        return returnString
    }
}



