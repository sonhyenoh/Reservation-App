package com.example.reservationapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        var restaurantName = editText_resName.text.toString()
        var userID = editText_userID.text.toString()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference(userID)

        //val nameRef = myRef.child("Restaurant Name")

        button_DB_submit.setOnClickListener{

            restaurantName = editText_resName.text.toString()
            userID = editText_userID.text.toString()

            var now = System.currentTimeMillis()
            var date = Date(now)
            var sdfNow = SimpleDateFormat("yyyyMMddHHmmss")
            var formatDate : String = sdfNow.format(date)

            var reservationInformation = ReservationInformation(restaurantName,userID,formatDate)

            myRef.child("Restaurant Name").setValue(restaurantName)
            myRef.child("Reservation Time").setValue(formatDate)
            myRef.child("Reservation Information").setValue(reservationInformation)
        }



        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                var str : String = "zxcv"
                val value = dataSnapshot.child("Reservation Time").getValue(String::class.java)
                if(value != null){
                    Toast.makeText(this@DatabaseTestActivity,str,Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Value is: $value")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

    }

    companion object {
        private const val TAG = "DatabaseTestActivity"
    }
}