package com.taidang.onemappoc

import android.support.annotation.DrawableRes
import com.mapbox.mapboxsdk.geometry.LatLng

data class Location(@DrawableRes val drawableResId: Int, val name: String, val brief: String, val location: LatLng)

const val MAPBOX_TOKEN = "pk.eyJ1IjoidGFpZGFuZ3ZhbiIsImEiOiJjamtqZ2plNGcxOGYzM2t1azU2ZGM0NjgwIn0.eFXXl5qBfYd1wdF9iaP0AQ"

const val ONEMAP_STYLE_DEFAULT = "https://maps-json.onemap.sg/Default.json"
const val ONEMAP_STYLE_GREY = "https://maps-json.onemap.sg/Grey.json"
const val ONEMAP_STYLE_DARK = "https://maps-json.onemap.sg/Night.json"
const val ONEMAP_STYLE_ORIGINAL = "https://maps-json.onemap.sg/Original.json"

val locationData = listOf(
        Location(R.drawable.ic_mess,
                "Location A1",
                "Lorem ipsum dolor sit amet",
                LatLng(1.331642, 103.807150)),
        Location(R.drawable.ic_mess,
                "Location A2",
                "Lorem ipsum dolor sit amet",
                LatLng(1.331632, 103.807074)),
        Location(R.drawable.ic_mess,
                "Location A3",
                "Lorem ipsum dolor sit amet",
                LatLng(1.331664, 103.807133)),
        Location(R.drawable.ic_pinter,
                "Location B",
                "Lorem ipsum dolor sit amet",
                LatLng(1.310033, 103.865106)),
        Location(R.drawable.ic_me,
                "Location C",
                "Lorem ipsum dolor sit amet",
                LatLng(1.283534, 103.838485)),
        Location(R.drawable.ic_fb,
                "Multiple items",
                "Item 1:Lorem ipsum dolor sit amet#Item 2:Lorem ipsum dolor sit amet#Item 3:Lorem ipsum dolor sit amet#Item 4:Lorem ipsum dolor sit amet",
                LatLng(1.250932, 103.823096))
)