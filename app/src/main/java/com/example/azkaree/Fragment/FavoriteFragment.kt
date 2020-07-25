package com.example.nurbk.ps.appathkar.Fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.azkaree.Adapter.CategoriesAdapter
import com.example.azkaree.R
import com.example.azkaree.db.DatabaseHelper
import com.example.azkaree.model.Athker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() ,CategoriesAdapter.onClick {

    val data by lazy {
        ArrayList<Athker>()
    }
    val adapter by lazy {
        CategoriesAdapter(activity!!, data, R.layout.item_design_favorite,this)
    }
    val db by lazy {
        DatabaseHelper(activity!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        activity!!.toolbar_main.setTitle("المفضلة")


        for (i in db!!.getAllData()) {
            if (i.status == 1) {
                data.add(i)
            }
        }
        Log.e("www",db.getAllData().toString())


        if (data.isNotEmpty()){
            root.favorite_recycler_view.visibility =View.VISIBLE
        root.favorite_recycler_view.layoutManager = LinearLayoutManager(activity)
        root.favorite_recycler_view.setHasFixedSize(true)
        root.favorite_recycler_view.adapter=adapter
        }else{
            root.favorite_recycler_view.visibility =View.GONE
            root.fav_logo.visibility =View.VISIBLE
            root.fav_txtnote.visibility =View.VISIBLE
        }
        return root
    }

    override fun onClickItem(position: Int, type: Int) {
        when(type){
            1->{
                if (data.isEmpty()) {
                    favorite_recycler_view.visibility = View.GONE
                    fav_logo.visibility = View.VISIBLE
                    fav_txtnote.visibility = View.VISIBLE
                }
            }
        }
    }


}
