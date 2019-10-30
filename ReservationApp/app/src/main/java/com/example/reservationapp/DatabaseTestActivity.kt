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

    val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_test)

        var resName = editText_resName.text.toString()
        var userID  : String
        var resTime : String
        var waitNum = String
        var waitCount = 0

        var masterDbNum : Int
        var myDbResName : String
        var myDbUserID : String
        var myDbResTime : String
        var myDbWaitNum : String
        var myDbUserInfo : ReservationInformation

        var myRef = database.getReference(resName)

        Log.d("zxcv", "A")

        // QR 코드를 찍으면 실행되는 함수라고 가정한다.
        button_DB_submit.setOnClickListener{
            resName = editText_resName.text.toString()
            userID = editText_userID.text.toString()
            resTime = getTime()

            Log.d("zxcv", "B")

            myRef = database.getReference(resName)

            Log.d("zxcv", "C")

            val userInfo = ReservationInformation(resName, userID, resTime, -1)

//            myRef.child("User ID").setValue(userID)
//            myRef.child("Restaurant Name").setValue(resName)
//            myRef.child("Reservation Time").setValue(resTime)
//            myRef.child("Waiting Number").setValue(waitNum)
            myRef.child(userID).setValue(userInfo)
            Log.d("zxcv", "D")
        }



        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                userID = editText_userID.text.toString()
                Log.d("zxcv", "E")
//                myDbUserID = dataSnapshot.child("User ID").getValue(String::class.java)?:"X"
//                myDbResName = dataSnapshot.child("Restaurant Name").getValue(String::class.java)?:"Restaurant Name"
//                myDbResTime = dataSnapshot.child("Reservation Time").getValue(String::class.java)?:"00000000000000"
//                myDbWaitNum = dataSnapshot.child("Waiting Number").getValue(String::class.java)?:"-1"

                // 여기서 에러
                myDbUserInfo = dataSnapshot.child(userID).getValue(ReservationInformation::class.java)?:ReservationInformation("X", "Res Name", "00000000000000", -1)

                myDbUserInfo = dataSnapshot.child(userID).getValue("")
                Log.d("zxcv", "F")
                myRef = database.getReference(userID)

                Log.d("zxcv", "G")
//                textView_userID.text = myDbUserID
//                textView_resName.text = myDbResName
//                textView_resTime.text = myDbResTime
                textView_waitNum.text = myDbUserInfo.toString()
                Log.d("zxcv", "H")
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