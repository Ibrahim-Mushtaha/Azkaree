package com.ibrahim7.azkaree.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import com.ibrahim7.azkaree.R
import com.ibrahim7.azkaree.model.MyApp
import com.ibrahim7.azkaree.model.SharedPrefTheme
import kotlinx.android.synthetic.main.splash2_activity.*

class splash : AppCompatActivity() {

    var sharedRefrance : SharedPrefTheme? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash2_activity)

        MyApp.sharedRefrance = SharedPrefTheme(this)

        if ( MyApp.sharedRefrance!!.mySharedPref!!.getBoolean("NightMode",false)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                splash.setBackgroundColor(Color.rgb(43, 43, 43))
                txt.setTextColor(Color.rgb(255, 255, 255))
                window.statusBarColor = Color.rgb(43, 43, 43)
            }
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                splash.setBackgroundColor(Color.rgb(255, 255, 255))
                window.statusBarColor = Color.rgb(255, 255, 255)
                txt.setTextColor(Color.rgb(43, 43, 43))
            }
        }



        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        main_logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_in))
        Handler().postDelayed({
            main_logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_out))
            Handler().postDelayed({
                val intenttomain = Intent(this, ViewPagerScreen::class.java)
                main_logo.visibility = View.GONE
                intenttomain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intenttomain)
                finish()
            }, 500)
        }, 700)
    }
}
