package com.example.nurbk.ps.appathkar.Fragment

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.azkaree.Fragment.AboutApp
import com.example.azkaree.R
import com.example.azkaree.model.MyApp
import com.example.azkaree.model.MyApp.Companion.sharedRefrance
import com.example.azkaree.model.SettingSystemString
import com.example.azkaree.model.SharedPrefTheme
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setting.view.*
import kotlinx.android.synthetic.main.update_dialog.*
import kotlinx.android.synthetic.main.update_dialog.seekBar



class SettingFragment : Fragment() {

    private val share by lazy {
        activity!!.getSharedPreferences(SettingSystemString.NAME_FILE_SHER, Context.MODE_PRIVATE)
    }

    private val editor by lazy {
        share.edit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedRefrance = SharedPrefTheme(activity!!)
        MyApp.CheckTheme(activity as AppCompatActivity)

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_setting, container, false)
        activity!!.toolbar_main.setNavigationIcon(null)
        activity!!.navigation_view.visibility = View.VISIBLE
        activity!!.toolbar_main.setTitle("الإعدادات")

        Log.e("asdqw",(share.getLong("seekbar",  share.getLong(SettingSystemString.COUNTER_NOTIFICATION, 20 * 60000))/60000).toInt().toString())
        root.shareapp.setOnClickListener {
            /*  val intent = Intent()
            var api =activity!!.applicationContext.applicationInfo
            var apk = api.sourceDir
            intent.action = Intent.ACTION_SEND
            intent.type = "application/vnd.android.package-archive"
            intent.putExtra(Intent.EXTRA_STREAM,File(apk))
            startActivity(Intent.createChooser(intent, "share via: "))*/

            val message: String = "Azkaree App"

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Please select app: "))
        }

        root.btn_about_app.setOnClickListener {
            MyApp.replaceFragmentwithNull(AboutApp(), activity as AppCompatActivity)
        }


        root.show_dialog.setOnClickListener {
            val mDialog = Dialog(activity!!)
            mDialog.setContentView(R.layout.update_dialog)
            mDialog.show()

            mDialog.seekBar.progress =
                (share.getLong("seekbar",  share.getLong(SettingSystemString.COUNTER_NOTIFICATION, 20 * 60000))/60000).toInt()

            mDialog.txtTimeSBar_setting.text = (share.getLong("seekbar",share.getLong(SettingSystemString.COUNTER_NOTIFICATION,
                20 * 60000))/60000).toString()


            mDialog.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seek: SeekBar?, num: Int, p2: Boolean) {
                    mDialog.txtTimeSBar_setting.text = num.toString()
                }

                override fun onStartTrackingTouch(seek: SeekBar?) {
                }

                override fun onStopTrackingTouch(seek: SeekBar?) {
                    editor.putLong(SettingSystemString.COUNTER_NOTIFICATION,(mDialog.txtTimeSBar_setting.text.toString().toInt()*60000).toLong()).apply()
                }
            })

            mDialog.btn_save_edit.setOnClickListener {
                editor.putLong("seekbar",(mDialog.txtTimeSBar_setting.text.toString().toInt()*60000).toLong()).apply()
                AthkerTypeFragment.startAlam(
                    activity!!,
                    share.getLong("seekbar", 20 * 60000)
                )
                Log.e("asdqw","seekbar")
                mDialog.dismiss()
            }

        }



        if (sharedRefrance!!.loadNightModeState()==true){
            root.btn_switch_theme.setChecked(true)
        }

        root.btn_switch_theme.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked){
                    sharedRefrance!!.setNightModeState(true)
                    MyApp.replaceFragment(SettingFragment(),activity as AppCompatActivity)
                }
                else{
                    sharedRefrance!!.setNightModeState(false)
                    MyApp.replaceFragment(SettingFragment(),activity as AppCompatActivity)

                }
            }
        })

        return root
    }

}
