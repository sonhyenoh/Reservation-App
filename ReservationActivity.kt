package com.example.qreservationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.integration.android.IntentIntegrator
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_reservation.*


class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        initScan()
    }
    private fun initScan(){
        IntentIntegrator(this).initiateScan()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result !=null){
            if(result.contents== null){
                //the result data is null or empty then/
                Toast.makeText(this,"this data is empty",Toast.LENGTH_LONG).show()
            }else{
                //this error because result maybe empty so use settext
                text_number.text = result.contents.toString()
                if(result.contents.toString() == "0"){
                    val intent = Intent(this, InStoreActivity::class.java)
                    startActivity(intent)
                }
            }
        }else {
            //the camera will not close if the result is still null
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
