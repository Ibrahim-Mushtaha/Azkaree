package com.example.nurbk.ps.appathkar.Fragment

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.azkaree.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_musbiha.view.*
import kotlinx.android.synthetic.main.selectnumber_popup.*
import java.util.*


class MusbihaFragment : Fragment() {

    val share by lazy {
        activity!!.getSharedPreferences("file", Activity.MODE_PRIVATE)
    }
    val editor by lazy {
        share.edit()
    }

    private var count = 0
    private var selectednum = 0
    private lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_musbiha, container, false)
        activity!!.toolbar_main.setTitle("المسبحة")
        count = share.getInt("count", 0)
        selectednum = share.getLong("number",100).toInt()

        RandomText()
        setCount(count)

        root.btnCount.setOnClickListener {
            if (count < share.getLong("number",100).toInt()) {
                count++
                setCount(count)
            }else{
                val vibrate = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                vibrate.vibrate(700)
                Toast.makeText(activity!!,"أتممت العدد المحدد سيتم تصفير العداد",Toast.LENGTH_SHORT).show()
                count = 0
            }
        }


        root.btn_spacfice_number.setOnClickListener {
            chooseNumber()
        }

        root.btnRestart.setOnClickListener {
            count = 0
            setCount(0)
        }

        return root
    }


    private fun setCount(i:Int){
        root.circle_count_number.text = i.toString()
        editor.putInt("count", i).apply()
    }


    fun chooseNumber(){
        val mDialog = Dialog(activity!!)
        mDialog.setContentView(R.layout.selectnumber_popup)
        mDialog.show()
        mDialog.btn_save.setOnClickListener {
            if (mDialog.etxt_number.text.isNotEmpty()) {
                editor.putLong("number", mDialog.etxt_number.text.toString().toLong())
                mDialog.dismiss()
            }else{
                editor.putLong("number", 1000)
                mDialog.dismiss()
            }
            setCount(0)
            count = 0
        }
    }

    fun RandomText(){
        Thread(Runnable {
            while (true){
                Thread.sleep(1*60000)
                var azkare =
                    arrayOf("الحمدلله", "لا اله الا الله", "الله أكبر", "استغفر الله العظيم","لا حول ولا قوة الا بالله","سبحان الله و بحمده")
                var random = Random()
                var num = random.nextInt(azkare.size)
                root.txt.setText(azkare[num])
            }
        }).start()
    }

}