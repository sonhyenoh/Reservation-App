package com.example.reservationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW))
        }

        // If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        intent.extras?.let {
            for (key in it.keySet()) {
                val value = intent.extras?.get(key)
                Log.d(TAG, "Key: $key Value: $value")
            }
        }
        // [END handle_data_extras]

        var buttonQR : ImageButton = findViewById(R.id.newActivity)
        buttonQR.setBackgroundResource(R.drawable.hand)

        var button_confirm : Button = findViewById(R.id.button_confirm)

        buttonQR.setOnClickListener {
            val intent : Intent = Intent(this, QRScanActivity::class.java)
            startActivity(intent)
        }

        button_confirm.setOnClickListener {

            Log.d(TAG, "Subscribing to reservation topic")
            // [START subscribe_topics]
            FirebaseMessaging.getInstance().subscribeToTopic("reservation")
                .addOnCompleteListener { task ->
                    var msg = getString(R.string.msg_subscribed)
                    if (!task.isSuccessful) {
                        msg = getString(R.string.msg_subscribe_failed)
                    }
                    Log.d(TAG, msg)
                    // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }
            // [END subscribe_topics]

            // Get token
            // [START retrieve_current_token]
            FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w(TAG, "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and toast
                    val msg = getString(R.string.msg_token_fmt, token)
                    Log.d(TAG, msg)
                    // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                })
            // [END retrieve_current_token]

            val intent: Intent = Intent(this, ConfirmActivity::class.java)
            startActivity(intent)
        }

        button_bossActivity.setOnClickListener {
            val intent: Intent = Intent(this, DatabaseTestActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}