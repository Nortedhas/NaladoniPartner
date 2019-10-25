package com.ageone.naladonipartner.Application

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import com.ageone.naladoni.SCAG.DataBase
import com.ageone.naladonipartner.External.Base.Activity.BaseActivity
import com.ageone.naladonipartner.External.Extensions.Activity.*
import com.ageone.naladonipartner.External.HTTP.update
import com.ageone.naladonipartner.External.Libraries.Alert.alertManager
import com.ageone.naladonipartner.External.Libraries.Alert.blockUI
import com.ageone.naladonipartner.External.Libraries.Alert.single
import com.ageone.naladonipartner.Models.User.user
import com.ageone.naladonipartner.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

class AppActivity: BaseActivity() {

    var fusedLocationClient: FusedLocationProviderClient? = null
    var locationRequest: LocationRequest? = null
    var locationCallback: LocationCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //only vertical mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        setFullScreen()
        setDisplaySize()

        addCameraPermissions()
        verifyPermissions {
            if (hasPermissions(PERMISSIONS_CAMERA)) {
                user.permission.camera = true
            }
        }


//        FuelManager.instance.basePath = DataBase.url

        user.isAuthorized = false //TODO: change after add registration
        coordinator.setLaunchScreen()
        Promise<Unit> { resolve, _ ->

            router.layout.setOnApplyWindowInsetsListener { _, insets ->
                utils.variable.statusBarHeight = insets.systemWindowInsetTop

                resolve(Unit)
                insets
            }

        }.then {
            coordinator.start()
            /*api.handshake {
                //todo user phone != null
                Timber.i("Handshake out")
                coordinator.start()

                FirebaseInstanceId.getInstance().instanceId
                    .addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Timber.i("fail")
                            return@OnCompleteListener
                        }

                        // Get new Instance ID UserHandshake
                        val token = task.result?.token ?: ""
                        DataBase.User.update(user.hashId, mapOf("fcmToken" to token))
                    })

            }*/


            val googleApiAvailability = GoogleApiAvailability.getInstance()
            when (val result = googleApiAvailability.isGooglePlayServicesAvailable(this)) {
                ConnectionResult.SUCCESS -> {
                    coordinator.start()
                }
                else -> {
                    googleApiAvailability.showErrorNotification(this, result)
                }
            }

        }

        setContentView(router.layout)
    }

    override fun onBackPressed() {
        Timber.i("viewBack")
        router.onBackPressed()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResult: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResult.isNotEmpty() && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    if (hasPermissions(PERMISSIONS_CAMERA)) {
                        user.permission.camera = true
                    }
                } else {
                    Toast.makeText(this, "Camera permission missing", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}