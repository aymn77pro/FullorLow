package com.example.fullorcharge

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
private val TAG:String?=MainActivity::class.simpleName
private var BeteryLevel:TextView? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BeteryLevel = findViewById(R.id.BettLevel)
    val intent = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(bttryLevelBroadcastReceiver,intent)
        val intentLOW = IntentFilter(Intent.ACTION_BATTERY_LOW)

    }

    private val bttryLevelBroadcastReceiver:BroadcastReceiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "android.intent.action.BATTERY_CHANGED"){
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1)
                BeteryLevel?.post {
                    if (level in 16 .. 99){
                        BeteryLevel?.text = "BATTERY NORMAL"
                    }else if (level in 0 .. 15 ) {
                        BeteryLevel?.text = "BATTERY LOW"
                    } else BeteryLevel?.text = "BATTERY IS FULL"
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(bttryLevelBroadcastReceiver)
    }
}