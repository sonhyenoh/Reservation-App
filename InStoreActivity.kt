package com.example.qrcodereservationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class InStoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_store)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.instoremenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_back -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Id","ok7624583")
                startActivityForResult(intent,1)
                finish()
                true
            }
            R.id.action_call ->{
                //주문할려는 list들이 나오면 됨
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
