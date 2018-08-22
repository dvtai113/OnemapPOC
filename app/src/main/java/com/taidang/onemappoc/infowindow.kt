package com.taidang.onemappoc

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.maps.MapboxMap

class CustomInfoWindow(private val context: Context) : MapboxMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View? {
        val view = View.inflate(context, R.layout.custom_info_window, null)

        view.findViewById<TextView>(R.id.title).text = marker.title
        view.findViewById<TextView>(R.id.subtitle).text = marker.snippet

        return view
    }
}


class PageInfoWindowAdapter(private val context: Context, private val action: (View, String, String) -> Unit) : MapboxMap.InfoWindowAdapter {

    override fun getInfoWindow(marker: Marker): View? {
        val container = View.inflate(context, R.layout.pager_info_window, null) as ViewGroup
        val viewpager = container.findViewById<ViewPager>(R.id.viewpager)
        viewpager.adapter = PopupPageAdapter(marker.snippet.split("#"), action)

        return container
    }
}
