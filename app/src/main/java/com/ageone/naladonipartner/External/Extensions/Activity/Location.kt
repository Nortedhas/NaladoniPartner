package com.ageone.naladonipartner.External.Extensions.Activity

import com.ageone.naladonipartner.Application.AppActivity
import com.ageone.naladonipartner.Models.User.user
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import timber.log.Timber

val locationBase = LatLng(56.838607, 60.605514)

fun AppActivity.fetchLastLocation(){
    /*fusedLocationClient?.
        lastLocation?.addOnSuccessListener{ location ->
        if (location != null) {
            currentLocation = location
        } else {
            Toast.makeText(this, "No Location recorded", Toast.LENGTH_SHORT).show()
        }
    }*/

    getLocationUpdates()
}


private fun AppActivity.getLocationUpdates() {

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    locationRequest = LocationRequest().apply {
        interval = 1 * 1000
        fastestInterval = 1 * 1000
        smallestDisplacement = 100f // 100 m
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return

            if (locationResult.locations.isNotEmpty()) {
                // get latest location
                val location = locationResult.lastLocation
                // use your location object
                // get latitude , longitude and other info from this
                Timber.i("Newest location: ${location.latitude}")
                user.location.currentLocation  = location
            }
        }
    }
}

//start location updates
fun AppActivity.startLocationUpdates() {
    fusedLocationClient?.requestLocationUpdates(
        locationRequest,
        locationCallback,
        null /* Looper */
    )
}

// stop location updates
fun AppActivity.stopLocationUpdates() {
    fusedLocationClient?.removeLocationUpdates(locationCallback)
}

var startLocation: LatLng = locationBase
    get() {
    return if (user.permission.geo) {
            LatLng(
                user.location.currentLocation?.latitude ?: locationBase.latitude,
                user.location.currentLocation?.longitude ?: locationBase.longitude
            )
        } else {
            locationBase
        }

}