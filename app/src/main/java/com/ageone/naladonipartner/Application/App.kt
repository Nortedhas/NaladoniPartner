package com.ageone.naladonipartner.Application

import android.app.Application
import android.content.Intent
import android.util.Log
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladonipartner.Application.Coordinator.Router.Router
import com.ageone.naladonipartner.BuildConfig
import com.ageone.naladonipartner.External.Base.Activity.BaseActivity
import com.ageone.naladonipartner.External.Extensions.Application.FTActivityLifecycleCallbacks
import com.ageone.naladonipartner.External.HTTP.API.API
import com.ageone.naladonipartner.External.Libraries.Alert.alertManager
import com.ageone.naladonipartner.External.Libraries.Alert.blockUI
import com.ageone.naladonipartner.External.Libraries.Alert.single
import com.ageone.naladonipartner.Internal.Utilities.Utils
import com.ageone.naladonipartner.Models.RxData
import com.ageone.naladonipartner.Network.Socket.WebSocket
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.gms.maps.MapView
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import net.alexandroid.shpref.ShPref
import timber.log.Timber

val router = Router()
val coordinator = FlowCoordinator()

val utils = Utils()
val api = API()
//val database = DataBase
val rxData = RxData()
var webSocket = WebSocket()
var intent = Intent()

val currentActivity: BaseActivity?
    get() = App.instance?.mFTActivityLifecycleCallbacks?.currentActivity as BaseActivity


class App: Application()  {

    init {
        instance = this
    }

    val mFTActivityLifecycleCallbacks = FTActivityLifecycleCallbacks()

    override fun onCreate() {
        super.onCreate()

        // MARK: SharePreferences

        ShPref.init(this, ShPref.APPLY)

        // MARK: Timber
        if (BuildConfig.DEBUG) {
            val deviceManufacturer = android.os.Build.MANUFACTURER
            if (deviceManufacturer.toLowerCase().contains("huawei")) {
                Timber.plant(HuaweiTree())
            } else {
                Timber.plant(Timber.DebugTree())
            }
        }

        ReactiveNetwork
            .observeNetworkConnectivity(applicationContext)
            .flatMapSingle<Any> { connectivity -> ReactiveNetwork.checkInternetConnectivity() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnected ->
                Timber.i("Internet: $isConnected")
                if (isConnected is Boolean) {

                    utils.isNetworkReachable = isConnected
                    if (!utils.isNetworkReachable) {
                        Timber.i("Internet are not allowed")
                        alertManager.blockUI(true)
                        alertManager.single(
                            "Отсутствует интернет соединение",
                            "В данный момент интернет соединение отстутсвует, либо соединение с сетью нестабильно")
                    } else {
                        Timber.i("Internet are allowed")
                        alertManager.blockUI(false)
                    }
                }

            }

        Realm.init(this)

        registerActivityLifecycleCallbacks(mFTActivityLifecycleCallbacks)
    }

    companion object {

        var instance: App? = null

    }
}

class HuaweiTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var priority = priority
        if (priority == Log.VERBOSE || priority == Log.DEBUG)
            priority = Log.INFO
        super.log(priority, tag, message, t)
    }
}

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Timber.i("${remoteMessage?.data}")

    }

    override fun onNewToken(token: String?) {
        Timber.i("$token")
    }
}