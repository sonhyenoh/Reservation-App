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

        button_DB_submit.setOnClickListener{

            var now = System.currentTimeMillis()
            var date = Date(now)
            var sdfNow = SimpleDateFormat("yyyyMMddHHmmss")
            var formatDate : String = sdfNow.format(date)

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("message")

            myRef.setValue("Hello, world!")

            // Read from the database
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)
                    Log.d(TAG, "Value is: $value")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })
        }
    }

    companion object {
        private const val TAG = "DatabaseTestActivity"
    }
}