package com.ibrahim7.azkaree.Receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.ibrahim7.azkaree.Service.MyService


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        MyService().getAction(intent,context)
    }
}
