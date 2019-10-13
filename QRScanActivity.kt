package com.example.reservationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_qr_scan.*
import android.widget.TextView

class QRScanActivity() : AppCompatActivity()  {
    var resultcheck = "null"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scan)
        initFunc()
        resultcheck = intent.extras?.get("Result").toString()
    }

    private fun initFunc() {
        //implement button action
        btn_scan_me.setOnClickListener {
            initScan()
        }
    }

    private fun initScan() {
        IntentIntegrator(this).initiateScan()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                //the result data is null or empty then/
                Toast.makeText(this, "this data is empty", Toast.LENGTH_LONG).show()
            } else {
                //this error because result maybe empty so use settext
                //et_value.setText(result.contents.toString())
                //numberChange(result.contents.toInt())

                val intentconfirm = Intent(this, ConfirmActivity()::class.java)
                //여기서 number대신에 "23"을 넣어도 바뀌지않은 Activity가 나온다 수정된것이 나오도록 해야한다
                intentconfirm.putExtra("Result", result.contents.toString())
                startActivityForResult(intentconfirm,1)
                finish()
                //20191007 여기서 받은 데이터 값을 넘겨주면 될듯
                //number_value_setText(result.contents.toString())
                // 이러면 수벌 수행되지가 않는데. 단지 윗줄만 적었을뿐인데 intent가 되지않는다.
                //...https://developer.android.com/reference/android/widget/EditText 이거 다시 읽어보기
                //지금까지 한거 qr코드를 인식해도 initintent 함수가 작동이 안됨 confirm으로 안 넘어가짐;; edittext거기서
                //text를 수정한게 반영이 안되서 activity가 튕기는 듯 함
                //대기번호 number_value부분을 textview로 해야할지 edittext로 해야할지 잘 모르겠삼


            }
        } else {
            //the camera will not close if the result is still null
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        if(resultcheck !="null") { //이렇게 두는 이유 만약 qr코드로 data 받아서 예약번호 다받고 그다음 실수로 예약하기를 다시눌렀을때 다시 back눌러도 main에 qr로 받은 data를 넘겨줘야해서
            val intentmain = Intent(this, MainActivity::class.java)
            intentmain.putExtra("ResultBack",resultcheck)
            startActivity(intentmain)
            finish()
        }else{
            val intentmain = Intent(this, MainActivity::class.java)
            startActivity(intentmain)
            finish()
        }
        super.onBackPressed()
    }
}