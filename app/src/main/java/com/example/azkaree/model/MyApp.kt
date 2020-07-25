package com.example.azkaree.model

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.azkaree.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MyApp : Application() {


    companion object {
        var sharedRefrance : SharedPrefTheme? = null

        fun replaceFragment(f: Fragment,activity: AppCompatActivity) {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, f).commit()
        }

        fun replaceFragmentwithNull(f: Fragment, activity: AppCompatActivity) {
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainContainer, f).addToBackStack(null).commit()
        }


        fun addbackArrow(activity: AppCompatActivity){
            activity!!.navigation_view.visibility = View.GONE
            activity!!.toolbar_main.setNavigationIcon(
                if (Locale.getDefault().displayLanguage == "English"){
                    R.drawable.ic_arrow_en
                }else{
                    R.drawable.ic_arrow_ar
                }
            )
            activity!!.toolbar_main.setNavigationOnClickListener {
                activity!!.onBackPressed()
            }
        }

        fun CheckTheme(activity: AppCompatActivity){
            sharedRefrance = SharedPrefTheme(activity!!)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (sharedRefrance!!.loadNightModeState() == true) {
                    activity!!.window.statusBarColor = Color.rgb(66, 66, 66)
                    activity!!.toolbar_main.setBackgroundColor( Color.rgb(66, 66, 66))
                    activity!!.setTheme(R.style.darktheme)
                    activity!!.navigation_view.setBackgroundColor(Color.rgb(66, 66, 66))
                } else if (sharedRefrance!!.loadNightModeState() == false) {
                    activity!!.setTheme(R.style.AppTheme)
                    activity!!.window.statusBarColor = Color.rgb(0, 205, 107)
                    activity!!.toolbar_main.setBackgroundColor( Color.rgb(0, 205, 107))
                    activity!!.navigation_view.setBackgroundColor(Color.WHITE)

                }
            }
        }

    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel1 = NotificationChannel(
                SettingSystemString.CHANNEL_ID,
                "Channel 1",
                NotificationManager.IMPORTANCE_HIGH
            )

            channel1.description = "This is Channel 1"


            val manager =
                getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(channel1)
        }
    }

}