package com.example.azkaree.Adapter

import android.app.Activity
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.azkaree.R
import com.example.azkaree.db.DatabaseHelper
import com.example.azkaree.model.Athker
import kotlinx.android.synthetic.main.item_design_categories.view.*
import kotlinx.android.synthetic.main.item_design_favorite.view.*
import java.util.ArrayList


class CategoriesAdapter(
    var activity: Activity, var data: MutableList<Athker>, var layout : Int , val itemclick: onClick
) :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {

    val db by lazy {
        DatabaseHelper(activity!!)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val tvdescripton = itemView.Categories_text
            val tvcopy = itemView.copy
            val btnCounter = itemView.btnItem
            val txtCounter = itemView.btn_click
            val btnFav = itemView.add_to_favorite

        // favorite

        val tvtitle = itemView.favorite_text
        val tvremove = itemView.btn_remove_from_favorite
        val tvcopyfav = itemView.btn_fav_copy
        val  tvcard = itemView.tvfavoritecard

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        if (layout == R.layout.item_design_categories) {
            Categoriesitem(holder,position)
        }else{
            FavoriteItem(holder,position)
        }
    }


    fun updateNote(arrayList: java.util.ArrayList<Athker>, position: Int, state: Int) {
        db!!.setUpateAthker(
            arrayList[position].id,
            arrayList[position].status
        )
    }

    interface onClick {
        fun onClickItem(position: Int,type:Int)
    }


    companion object{
        fun copyText(position: Int , activity: Activity ,arrayList: ArrayList<Athker>){
            val clipboard =
                activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
            clipboard!!.text=arrayList!![position].content
            Toast.makeText(activity!!,"تم نسخ النص",Toast.LENGTH_SHORT).show()
        }
    }


    fun  FavoriteItem(holder: MyViewHolder, position: Int){
        holder.tvtitle.text = data[position].content.toString()


        holder.tvremove.setOnClickListener {
            if (data[position].status==1){
                data[position].status=0
                updateNote(data!! as ArrayList<Athker>,position,0)
                data.remove(data[position])
            }
            notifyDataSetChanged()
            itemclick.onClickItem(holder.adapterPosition,1)

        }

        holder.tvcopyfav.setOnClickListener {
            copyText(position, activity, data as ArrayList<Athker>)
        }
    }

    fun Categoriesitem(holder: MyViewHolder, position: Int){
        holder.tvdescripton.text = data[position].content


        holder.txtCounter.text = data[position].counter.toString()

        holder.btnFav.setImageResource(
            if (data[position].status == 1) {
                R.drawable.ic_favorite_1
            } else {
                R.drawable.ic_favorite_0
            }
        )

        holder.btnFav.setOnClickListener {
            if (data[position].status==0){
                data[position].status=1
                updateNote(data!! as ArrayList<Athker>,position,1)
            }else if (data[position].status==1){
                data[position].status=0
                updateNote(data!! as ArrayList<Athker>,position,0)
            }
            notifyDataSetChanged()
        }


        holder.tvcopy.setOnClickListener {
            copyText(position, activity, data as ArrayList<Athker>)
        }

        holder.btnCounter.setOnClickListener {
            holder.txtCounter.text = data[position].counter.toString()
            itemclick.onClickItem(position, 3)
        }

        holder.txtCounter.setOnClickListener {
            holder.txtCounter.text =data[position].counter.toString()
            Log.e("hzm",data[position].counter.toString())
            itemclick.onClickItem(position, 3)
        }
    }

}