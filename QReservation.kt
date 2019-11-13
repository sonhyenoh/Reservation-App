package com.example.reservationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qreservation.*

class QReservation : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qreservation)
        initScan()


        webView = findViewById(R.id.webview)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl("https://store.naver.com/restaurants/detail?id=35832273")
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
                text_storename.text = result.contents.toString()
            }
        } else {
            //the camera will not close if the result is still null
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
