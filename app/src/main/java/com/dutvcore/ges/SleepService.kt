package com.dutvcore.ges

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class SleepService : Service() {

    private val CHANNEL_ID = "SleepMonitorChannel"
    private val NOTIFICATION_ID = 1
    private val TAG = "SleepTracker"

    // Screen state listener
    private val screenStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent?.action) {
                Intent.ACTION_SCREEN_OFF -> {
                    Log.d(TAG, "Screen OFF -> Sleep started")
                    // Handle sleep start
                }
                Intent.ACTION_SCREEN_ON -> {
                    Log.d(TAG, "Screen ON -> Awake")
                    // Handle wake up
                }
            }
        }
    }

    // Init config
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        registerScreenReceiver()
    }

    // Main lifecycle
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Sticky Notification (Req for Foreground)
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("GES Monitor")
            .setContentText("Sleep tracking active...")
            .setSmallIcon(android.R.drawable.ic_lock_idle_alarm) // Sys default icon
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        startForeground(NOTIFICATION_ID, notification)

        // Auto-restart if OS kills process
        return START_STICKY
    }

    // Cleanup
    override fun onDestroy() {
        super.onDestroy()
        // Prevent mem leak
        unregisterReceiver(screenStateReceiver)
        Log.d(TAG, "Service destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? {
        // No UI binding req
        return null
    }

    // Dynamic receiver reg
    private fun registerScreenReceiver() {
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
            addAction(Intent.ACTION_SCREEN_OFF)
        }
        registerReceiver(screenStateReceiver, filter)
    }

    // Req for Android 8.0+
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Sleep Monitor Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }
}