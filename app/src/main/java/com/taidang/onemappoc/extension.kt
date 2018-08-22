package com.taidang.onemappoc

import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import com.mapbox.mapboxsdk.annotations.Icon
import com.mapbox.mapboxsdk.annotations.IconFactory

fun AppCompatActivity.createMarkerIcon(@DrawableRes drawableResId: Int): Icon {
    return IconFactory.getInstance(this).fromResource(drawableResId)
}