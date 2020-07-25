package com.example.azkaree.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azkaree.Adapter.CategoriesAdapter

import com.example.azkaree.R
import com.example.azkaree.db.DatabaseHelper
import com.example.azkaree.model.Athker
import com.example.azkaree.model.MyApp
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.categories_fragment.view.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class AzkareeDataFragment : Fragment(),CategoriesAdapter.onClick {
    val data by lazy {
        ArrayList<Athker>()
    }
    val adapter by lazy {
        CategoriesAdapter(activity!!, data, R.layout.item_design_categories,this)
    }
    val db by lazy {
        DatabaseHelper(activity!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MyApp.addbackArrow(activity as AppCompatActivity)
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.categories_fragment, container, false)
        val type = arguments
        activity!!.toolbar_main.setTitle(type!!.get("title").toString())

        for (i in db.getAllData()) {
            if (i.type == type!!.get("type")) {
                data.add(Athker(i.id, i.content, i.counter,  i.status,i.type))
            }
        }

        root.categories_recycler_view.layoutManager = LinearLayoutManager(activity)
        root.categories_recycler_view.adapter=adapter

        return root
    }




    override fun onClickItem(position: Int, type: Int) {
        when (type) {
            3 -> {
                CounterIsFinsihed(position)
            }
        }
    }

    fun CounterIsFinsihed(position: Int){
        data[position].counter--
        if (data[position].counter == 0) {
            data.remove(data[position])
        }
        adapter.notifyDataSetChanged()
    }

}
