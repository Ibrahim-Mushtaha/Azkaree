package com.example.azkaree.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.SeekBar
import com.demotxt.droidsrce.slide.IntroViewPagerAdapter
import com.example.azkaree.Activity.MainActivity.Companion.setLanguage
import com.example.azkaree.R
import com.example.azkaree.model.SettingSystemString
import com.example.viewpager.model.ViewPagerScreen
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.viewpager_screen.*

class ViewPagerScreen : AppCompatActivity() {

    private val data by lazy {
        ArrayList<ViewPagerScreen>()
    }
    private val share by lazy {
        getSharedPreferences("file", Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        share.edit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewpager_screen)
        //full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        checkViewPagerGetStarted()
       // AthkerFragment.startAlam(this,20*100000)

        setLanguage("ar",this)
        getViewpagerData()
        var introViewPagerAdapter = IntroViewPagerAdapter(this,data!!)
        viewPager.adapter = introViewPagerAdapter

        //to link the tab with view pager
        tab_indicator.setupWithViewPager(viewPager)

        btn_next.setOnClickListener {
            var position = 0
            position = viewPager.currentItem
            if (position < data.size){
                position++
                position++
                viewPager.setCurrentItem(position)
            }

            if (position == data.size -1){
                LoadlastScreen()
            }
        }


        btn_GetStarted.setOnClickListener {
            startActivity(Intent(this,
                MainActivity::class.java))
            editor.putBoolean(SettingSystemString.START_ALARM, true).apply()
            /// to check if i have been click to the button on not
            SavePrefsData()
            finish()
        }


        /*  tab_indicator.addOnTabSelectedListener()    */
        tab_indicator.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                // called when tab selected
                if (tab.position == data.size -1){
                    LoadlastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // called when tab unselected
                tab_indicator.visibility= View.VISIBLE
                btn_next.visibility = View.VISIBLE
                btn_GetStarted.visibility = View.GONE
                dialog2.visibility = View.GONE
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // called when a tab is reselected
            }
        })



        editor.putLong(SettingSystemString.COUNTER_NOTIFICATION,(txtTimeSBar.text.toString().toInt()*60000).toLong()).apply()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seek: SeekBar?, num: Int, p2: Boolean) {
                txtTimeSBar.text = num.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar?) {
            }

            override fun onStopTrackingTouch(seek: SeekBar?) {
                editor.putLong(SettingSystemString.COUNTER_NOTIFICATION,(txtTimeSBar.text.toString().toInt()*60000).toLong()).apply()
            }
        })
    }

    private fun checkViewPagerGetStarted(){
        if (restorePref()){
            startActivity(Intent(this,
                MainActivity::class.java))
            finish()
        }
    }


    private fun restorePref(): Boolean {
        var Pref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        var IsIntroActivityOpenedBefor =Pref.getBoolean("isIntroOpened",false)
        return IsIntroActivityOpenedBefor
    }

    private fun SavePrefsData() {
        var Pref = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        var editor = Pref.edit()
        editor.putBoolean("isIntroOpened",true)
        editor.commit()
    }

    private fun LoadlastScreen() {
        btn_GetStarted.visibility = View.VISIBLE
        tab_indicator.visibility= View.GONE
        dialog2.visibility = View.VISIBLE
        btn_next.visibility = View.GONE
        btn_GetStarted.startAnimation(AnimationUtils.loadAnimation(this,
            R.anim.btn_animation
        ))
    }

    fun getViewpagerData(){
        data.add(
            ViewPagerScreen("تطبيق أذكاري","يجعلك تعيش في تجربة إيمانية , و رؤيا نورانية\n" +
                    "في روحانيات الأذكار", R.drawable.ic_viewpager_back2
            )
        )
        data.add(
            ViewPagerScreen("أذكاري هي حياتي","ردد أذكارك صباحا و مساءا , تذكر نعم ربك \n" +
                    "عليك مع تطبيق أذكار", R.drawable.ic_viewpager_back2
            )
        )
        data.add(ViewPagerScreen("","", R.drawable.ic_viewpager_back2))
    }

}
