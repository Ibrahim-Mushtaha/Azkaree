package com.ibrahim7.azkaree.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.ibrahim7.azkaree.R
import com.ibrahim7.azkaree.db.DatabaseHelper
import com.ibrahim7.azkaree.model.MyApp
import kotlinx.android.synthetic.main.about_app_fragment.view.*

/**
 * A simple [Fragment] subclass.
 */
class AboutApp : Fragment() {
    val db by lazy {
        DatabaseHelper(activity!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MyApp.addbackArrow(activity as AppCompatActivity)
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.about_app_fragment, container, false)

        root.txt_version.text = db.databaseName.toString()
        return root
    }

}
