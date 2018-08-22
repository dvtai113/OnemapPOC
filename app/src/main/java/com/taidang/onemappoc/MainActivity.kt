package com.taidang.onemappoc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton


class MainActivity : AppCompatActivity() {

    private val mapView: MapView by lazy {
        this@MainActivity.findViewById<MapView>(R.id.mappview)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, MAPBOX_TOKEN)
        setContentView(R.layout.activity_main)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync {
            setupMapboxMap(it)
            addMarker(it)
        }

        findViewById<Button>(R.id.btnDefault).setOnClickListener { mapView.setStyleUrl(ONEMAP_STYLE_DEFAULT) }
        findViewById<Button>(R.id.btnGrey).setOnClickListener { mapView.setStyleUrl(ONEMAP_STYLE_GREY) }
        findViewById<Button>(R.id.btnDark).setOnClickListener { mapView.setStyleUrl(ONEMAP_STYLE_DARK) }
        findViewById<Button>(R.id.btnOriginal).setOnClickListener { mapView.setStyleUrl(ONEMAP_STYLE_ORIGINAL) }
    }

    private fun setupMapboxMap(map: MapboxMap) {
        // init map UI
        map.setStyle(ONEMAP_STYLE_GREY)
        map.uiSettings.isAttributionEnabled = false
        map.uiSettings.isLogoEnabled = false

        // init viewport
        val zoomLocation = LatLng(1.316463, 103.849876)
        val cameraPosition = CameraPosition.Builder()
                .target(zoomLocation)
                .zoom(11.0)
                .build()
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        // set marker/info-window click listener
        map.setOnMarkerClickListener {
            val cameraPosition = CameraPosition.Builder()
                    .target(it.position)
                    .build()
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

            // custom info window
            if (it.title!!.contains("Multiple")) {
                map.infoWindowAdapter = PageInfoWindowAdapter(this) { _, title, snippet ->
                    alert {
                        this.title = "Info"
                        this.message = "$title\n$snippet"
                        okButton { it.dismiss() }
                    }.show()
                }
                map.onInfoWindowClickListener = null
            } else {
                // custom info window
                map.infoWindowAdapter = CustomInfoWindow(this)
                map.setOnInfoWindowClickListener { marker ->
                    alert {
                        title = "Info"
                        message = "${marker.title}\n${marker.snippet}"
                        okButton { it.dismiss() }
                    }.show()
                    false
                }
            }
            false
        }
    }

    private fun addMarker(map: MapboxMap) {
        locationData.forEach {
            val markerOpt = MarkerOptions()
                    .position(it.location)
                    .icon(createMarkerIcon(it.drawableResId))
                    .title(it.name)
                    .snippet(it.brief)

            map.addMarker(markerOpt)
        }
    }

    //........................................
    // MapBox lifecycle
    //........................................
    public override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    public override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    public override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    public override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
    //........................................
}
