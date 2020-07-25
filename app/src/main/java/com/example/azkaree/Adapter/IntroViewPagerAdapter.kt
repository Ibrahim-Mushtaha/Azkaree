package com.demotxt.droidsrce.slide


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.azkaree.R

import com.example.viewpager.model.ViewPagerScreen
import kotlinx.android.synthetic.main.screen_layout.view.*

class IntroViewPagerAdapter(var activity: Activity, var data: MutableList<ViewPagerScreen>) : PagerAdapter() {
    override fun getCount(): Int {
        return data.size
    }

    internal lateinit var inflater: LayoutInflater




    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.screen_layout, container, false)
        view.intro_title.setText(data[position].title)
        view.intro_description.setText(data[position].description)
        view.intro_image.setImageResource(data[position].image)


        container.addView(view)



        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }
}
