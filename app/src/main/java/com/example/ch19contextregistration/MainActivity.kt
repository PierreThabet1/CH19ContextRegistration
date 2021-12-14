package com.example.ch19contextregistration

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    lateinit var receiver: MyReceiver
    val Log = Logger.getLogger(javaClass.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        receiver = MyReceiver()

        button.setOnClickListener {
            val intent = Intent("com.workingdev.DOSOMETHING")
            sendBroadcast(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val filter = IntentFilter("com.workingdev.DOSOMETHING")
        registerReceiver(receiver, filter)
        Log.info("Registered receiver")
    }

    override fun onPause() {
        super.onPause()

        try {
            unregisterReceiver(receiver)
            Log.info("Unregistered receiver")
        }

        catch (iae: IllegalAccessException) {
            Log.warning(iae.toString())
        }
    }
}