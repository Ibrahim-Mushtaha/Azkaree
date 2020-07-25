package com.example.nurbk.ps.appathkar.Fragment

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azkaree.Adapter.MainAzkareeAdapter
import com.example.azkaree.Fragment.AzkareeDataFragment
import com.example.azkaree.R
import com.example.azkaree.model.MyApp
import com.example.azkaree.model.SettingSystemString
import com.example.nurbk.ps.appathkar.Receiver.BroadcastReceiverNotifictionAlarm
import com.example.azkaree.Service.MyService
import com.example.viewpager.model.TypeAthker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_athker.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AthkerTypeFragment : Fragment(),MainAzkareeAdapter.onClick {

    private val bundle = Bundle()
    private val data by lazy {
        ArrayList<TypeAthker>()
    }

    private val share by lazy {
        activity!!.getSharedPreferences(SettingSystemString.NAME_FILE_SHER, Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        share.edit()
    }

    private val adapter by lazy {
        MainAzkareeAdapter(activity!!, data, this)
    }

    private val intentServiceS by lazy {
        Intent(activity, MyService::class.java)
    }
    private val intentServiceM by lazy {
        Intent(activity, MyService::class.java)
    }

    private var isStartServiceS = false
    private var isStartServiceM = false

    private var isStart = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // to get data
        getTypeData()
        if (share.getBoolean(SettingSystemString.START_ALARM, true)) {

            startAlam(
                activity!!,
                share.getLong(SettingSystemString.COUNTER_NOTIFICATION, 20 * 60000)
            )
            Log.e("asdqw","COUNTER_NOTIFICATION")
            editor.putBoolean(SettingSystemString.START_ALARM, false).apply()
        }

    }

    private lateinit var root: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_athker, container, false)
        activity!!.toolbar_main.setNavigationIcon(null)
        activity!!.navigation_view.visibility =View.VISIBLE
        activity!!.toolbar_main.setTitle(getText(R.string.app_name))

        getTime()



       // Log.e("asdqw",(share.getLong(SettingSystemString.COUNTER_NOTIFICATION, 20 * 60000)/60000).toString())

        root.main_Azkaree_recycler.layoutManager = LinearLayoutManager(activity)
        root.main_Azkaree_recycler.setHasFixedSize(true)
        root.main_Azkaree_recycler.adapter=adapter

        Log.e("asdqw",(share.getLong("seekbar",  share.getLong(SettingSystemString.COUNTER_NOTIFICATION, 20 * 60000))/60000).toInt().toString())

        root.btn_morning_sound.setOnClickListener {
            if (isStartServiceM) {
                activity!!.stopService(intentServiceM)
                isStartServiceM = false
            }
            if (isStartServiceS) {
                activity!!.stopService(intentServiceS)
                isStartServiceS = false

            } else {
                startService(R.raw.s, "أذكار الصباح مسموعة", intentServiceS)
                isStartServiceS = true

            }
        }

        root.btn_evening_sound.setOnClickListener {
            if (isStartServiceS) {
                activity!!.stopService(intentServiceS)
                isStartServiceS = false
            }
            if (isStartServiceM) {
                activity!!.stopService(intentServiceM)
                isStartServiceM = false

            } else {
                startService(R.raw.m, "أذكار المساء مسموعة", intentServiceM)
                isStartServiceM = true

            }
        }


        return root
    }



    override fun onClickItem(position: Int, type: Int) {
        when(type){
            1->{
                sendData(position)
            }
        }
    }


    fun getTime(){
        val time = SimpleDateFormat("HH : mm").format(Date())

        var timeNum = time.substring(0, 2).toInt()

        if (timeNum > 12) {
            root.imageTime.setImageResource(R.drawable.ic_moon)
            root.txtTime.text = "أسعد الله صباحكم"
        } else {
            root.imageTime.setImageResource(R.drawable.ic_sun)
            root.txtTime.text = "أسعد الله مسائكم"
        }
    }

    fun sendData(position: Int){
        bundle.putInt("type", position + 1)
        bundle.putString("title", data[position].title)
        val ahker = AzkareeDataFragment()
        ahker.arguments = bundle
        MyApp.replaceFragmentwithNull(ahker,activity as AppCompatActivity)
    }

    private fun getTypeData() {
        data!!.add(TypeAthker("أذكار الصباح",R.drawable.ic_morning))
        data!!.add(TypeAthker("أذكار المساء",R.drawable.ic_night))
        data!!.add(TypeAthker("أذكار النوم",R.drawable.ic_bedtime))
        data!!.add(TypeAthker("أذكار الاستيقاظ من النوم",R.drawable.ic_wakeup))
        data!!.add(TypeAthker("اذكار قبل الدخول الى المسجد",R.drawable.ic_mosque))

    }

    private fun startService(media: Int, name: String, intentService: Intent) {
        intentService.putExtra(SettingSystemString.MEDIA, media)
        intentService.putExtra(SettingSystemString.NAME_TYPE_ATHKER, name)
        ContextCompat.startForegroundService(activity!!, intentService)

    }

    companion object{
        fun startAlam(context: Context, time: Long) {
            val i = Intent(context, BroadcastReceiverNotifictionAlarm::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, i, 0)

            val alarm = context.getSystemService(Activity.ALARM_SERVICE) as AlarmManager

            alarm.setRepeating(
                AlarmManager.RTC_WAKEUP,
                time+System.currentTimeMillis(), AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }

    }


}
