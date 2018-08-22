package com.taidang.onemappoc

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class PopupPageAdapter(private val dataset: List<String>, private val action: (View, String, String) -> Unit) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = LayoutInflater.from(container.context).inflate(R.layout.page_item, container, false)
        val arr = dataset[position].split(":")
        itemView.findViewById<TextView>(R.id.tvTitle).text = arr[0]
        itemView.findViewById<TextView>(R.id.tvSubtitle).text = arr[1]
        itemView.setOnClickListener {
            action.invoke(itemView, arr[0], arr[1])
        }
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, obj: Any) = view == obj

    override fun getCount() = dataset.size

}