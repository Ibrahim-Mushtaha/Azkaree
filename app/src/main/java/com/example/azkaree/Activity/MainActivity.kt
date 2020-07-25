package com.example.azkaree.Activity

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.azkaree.R
import com.example.azkaree.Service.MyService
import com.example.azkaree.model.MyApp
import com.example.azkaree.model.MyApp.Companion.replaceFragment
import com.example.azkaree.model.SettingSystemString
import com.example.nurbk.ps.appathkar.Fragment.AthkerTypeFragment
import com.example.nurbk.ps.appathkar.Fragment.FavoriteFragment
import com.example.nurbk.ps.appathkar.Fragment.MusbihaFragment
import com.example.nurbk.ps.appathkar.Fragment.SettingFragment
import com.example.nurbk.ps.appathkar.Receiver.MyReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{

    val myReceiver = MyReceiver()

    private val share by lazy {
        getSharedPreferences("file", Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        share.edit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.athker -> {
                replaceFragment(AthkerTypeFragment(),this as AppCompatActivity)
                return true
            }
            R.id.setting -> {
                replaceFragment(SettingFragment(),this as AppCompatActivity)

                return true
            }
            R.id.mala -> {
                replaceFragment(MusbihaFragment(),this as AppCompatActivity)
                return true
            }
            R.id.favorite -> {
                replaceFragment(FavoriteFragment(),this as AppCompatActivity)
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyApp.CheckTheme(this)
        replaceFragment(AthkerTypeFragment(),this as AppCompatActivity)
        navigation_view.setOnNavigationItemSelectedListener(this)
        setSupportActionBar(toolbar_main)


        setLanguage("ar",this)
        onRegisterReceiverNotifiction()

    }

    private fun onRegisterReceiverNotifiction(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intentFilter = IntentFilter()
            intentFilter.addAction(SettingSystemString.NOTIFY_DELETE)
            registerReceiver(myReceiver, intentFilter)
        }
    }



    companion object{
         fun setLanguage(lan: String,context: Context) {
            val res = context.resources
            val dr = res.displayMetrics
            val cr = res.configuration
            cr.setLocale(Locale(lan))
            res.updateConfiguration(cr, dr)
        }
    }




    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this,MyService::class.java))
        unregisterReceiver(myReceiver)
    }
}
