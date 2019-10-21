package com.ageone.naladonipartner.External.Base.MapView

import android.widget.ImageView
import android.widget.RelativeLayout
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.External.Extensions.Activity.locationBase
import com.ageone.naladonipartner.Models.User.user
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import yummypets.com.stevia.dp

class BaseMapView: MapView(currentActivity) {

}

fun ImageView.setLocationButtonInMap(left: Int, top: Int, right: Int, bottom: Int) {
    val layoutParams = layoutParams as RelativeLayout.LayoutParams

    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
    layoutParams.setMargins(left.dp, top.dp, right.dp, bottom.dp)
}

fun GoogleMap.setMyLocation() {
    isMyLocationEnabled = true

    setOnMyLocationClickListener { location ->
        val loc = if (user.permission.geo) {
            LatLng(
                location.latitude,
                location.longitude
            )
        } else {
            locationBase
        }
        moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 13f))
    }
}