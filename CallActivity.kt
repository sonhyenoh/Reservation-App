package com.example.qrcodereservationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import kotlinx.android.synthetic.main.activity_call.*

class CallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val textView = findViewById(R.id.edit_autostore) as AutoCompleteTextView
        val store : Array<out String> = resources.getStringArray(R.array.store_array)
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, store).also { adapter ->
            textView.setAdapter(adapter)
        }

        bt_input.setOnClickListener{
            val intent = Intent(this, InStoreActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.callmenu, menu)
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}
