package com.example.reservationapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_database_test.*
import java.text.SimpleDateFormat
import java.util.*

class DatabaseTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_test)

        var userID = editText_userID.text.toString()
        var resName = editText_resName.text.toString()
        var resTime = getTime()
        var waitNum = "-1"
        var waitCount = 0

        var masterDbNum : Int
        var myDbUserID : String
        var myDbResName : String
        var myDbResTime : String
        var myDbWaitNum : String

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(userID)

        // QR 코드를 찍으면 실행되는 함수라고 가정한다.
        button_DB_submit.setOnClickListener{
            userID = editText_userID.text.toString()
            resName = editText_resName.text.toString()
            resTime = getTime()

            myRef.child("User ID").setValue(userID)
            myRef.child("Restaurant Name").setValue(resName)
            myRef.child("Reservation Time").setValue(resTime)
            myRef.child("Waiting Number").setValue(waitNum)
        }



        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                myDbUserID = dataSnapshot.child("User ID").getValue(String::class.java)?:""
                myDbResName = dataSnapshot.child("Restaurant Name").getValue(String::class.java)?:"Restaurant Name"
                myDbResTime = dataSnapshot.child("Reservation Time").getValue(String::class.java)?:"00000000000000"
                myDbWaitNum = dataSnapshot.child("Waiting Number").getValue(String::class.java)?:"-1"

                textView_userID.text = myDbUserID
                textView_resName.text = myDbResName
                textView_resTime.text = myDbResTime
                textView_waitNum.text = myDbWaitNum

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("zxcv", "Failed to read value.", error.toException())
            }
        })

    }

    private fun getTime() : String{
        return SimpleDateFormat("yyyyMMddHHmmss").format(Date(System.currentTimeMillis()))
    }

    fun regReservation(){

    }

    companion object {
        private const val TAG = "DatabaseTestActivity"
    }
}