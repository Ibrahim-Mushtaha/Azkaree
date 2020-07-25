package com.example.nurbk.ps.appathkar.Receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.azkaree.Service.MyService


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        MyService().getAction(intent,context)
    }
}
