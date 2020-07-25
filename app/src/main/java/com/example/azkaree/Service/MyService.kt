package com.example.azkaree.Service

import android.app.Activity
import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.azkaree.Activity.MainActivity
import com.example.azkaree.R
import com.example.azkaree.model.MyApp
import com.example.azkaree.model.SettingSystemString
import com.example.azkaree.model.SharedPrefTheme

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private val share by lazy {
        getSharedPreferences(SettingSystemString.NAME_FILE_SHER, Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        share.edit()
    }

    private lateinit var mediaPlayer: MediaPlayer
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mediaPlayer = MediaPlayer.create(this, intent!!.getIntExtra(SettingSystemString.MEDIA, 0))
        mediaPlayer.start()


        startForeground(
            1,
            createNotification(
                this,
                intent.getStringExtra(SettingSystemString.NAME_TYPE_ATHKER)!!
            )
        )

        getAction(intent, this)

        return START_NOT_STICKY
    }


    fun getAction(intent: Intent, context: Context) {

        if (intent.action == SettingSystemString.NOTIFY_DELETE) {
            context.stopService(Intent(context, MyService::class.java))
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()
        mediaPlayer.release()
    }


    fun createNotification(context: Context, title: String): Notification {

        val expandedView = RemoteViews(
            context.packageName,  if (MyApp.sharedRefrance!!.loadNightModeState() == true){
                R.layout.notification_azkar_sound
            }else{
                R.layout.notification
            })

        val delete = Intent(SettingSystemString.NOTIFY_DELETE)
        val pDelete =
            PendingIntent.getBroadcast(context, 0, delete, PendingIntent.FLAG_UPDATE_CURRENT)
        expandedView.setOnClickPendingIntent(R.id.btnDelete, pDelete)

        val nc = NotificationCompat.Builder(context, SettingSystemString.CHANNEL_ID)
        val notifyIntent = Intent(context, MainActivity::class.java)

        notifyIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        nc.setContentIntent(pendingIntent)
        nc.setSmallIcon(R.drawable.ic_play)
        nc.setAutoCancel(true)
        nc.setCustomContentView(expandedView)
        nc.contentView.setTextViewText(R.id.textSongName, title)

        return nc.build()
    }


}
