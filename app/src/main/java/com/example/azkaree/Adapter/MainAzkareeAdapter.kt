package com.example.azkaree.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.azkaree.R
import com.example.viewpager.model.TypeAthker
import kotlinx.android.synthetic.main.item_design_athker.view.*

class MainAzkareeAdapter(
    var activity: Activity, var data: MutableList<TypeAthker>, val itemclick: onClick
) :
    RecyclerView.Adapter<MainAzkareeAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvtitle = itemView.item_title_azkaree
        val  imgcard = itemView.item_image_azkaree
        val  tvcard = itemView.main_Azkaree_card
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.item_design_athker, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvtitle.text = data[position].title.toString()
        holder.imgcard.setImageResource(data[position].image)

        holder.tvcard.setOnClickListener{
            itemclick.onClickItem(holder.adapterPosition,1)
        }


    }


    interface onClick {
        fun onClickItem(position: Int,type:Int)
    }

}