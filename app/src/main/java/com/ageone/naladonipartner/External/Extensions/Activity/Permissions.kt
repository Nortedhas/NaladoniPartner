package com.ageone.naladonipartner.External.Extensions.Activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.ageone.naladonipartner.Application.AppActivity
import timber.log.Timber

const val REQUEST_CODE = 1
var permissions = emptyArray<String>()

fun Activity.hasPermissions(permissions: Array<String>): Boolean = permissions.all {
    ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}

// STORAGE
private val PERMISSIONS_STORAGE = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

fun Activity.addStoragePermissions() {
    permissions += PERMISSIONS_STORAGE
}

// LOCATION
val PERMISSIONS_LOCATION = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)

fun Activity.addLocationPermissions() {
    permissions += PERMISSIONS_LOCATION
}

// CAMERA
val PERMISSIONS_CAMERA = arrayOf(
    Manifest.permission.CAMERA
)

fun Activity.addCameraPermissions() {
    permissions += PERMISSIONS_CAMERA
}

fun AppActivity.verifyPermissions(completion: () -> Unit ) {

    if (!hasPermissions(permissions)) {
        ActivityCompat.requestPermissions(
            this,
            permissions,
            REQUEST_CODE
        )
    } else {
        completion.invoke()
    }
}


//TODO: replace in base
