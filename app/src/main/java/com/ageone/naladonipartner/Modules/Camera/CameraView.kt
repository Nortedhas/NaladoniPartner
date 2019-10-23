package com.ageone.naladonipartner.Modules.Camera

import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.hardware.Camera
import android.os.Build
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ageone.naladonipartner.Application.AppActivity
import com.ageone.naladonipartner.R
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.External.Libraries.Alert.alertManager
import com.ageone.naladonipartner.External.Libraries.Alert.single
import com.ageone.naladonipartner.Modules.Camera.rows.CameraCaptureViewHolder
import com.ageone.naladonipartner.Modules.Camera.rows.initialize
import timber.log.Timber
import yummypets.com.stevia.*
import java.io.IOException

class CameraView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI),SurfaceHolder.Callback, Camera.PictureCallback{


    val viewModel = CameraViewModel()

    private var surfaceHolder: SurfaceHolder? = null
    private var camera: Camera? = null
    private var surfaceView: SurfaceView? = null


    private val neededPermissions = arrayOf(CAMERA, WRITE_EXTERNAL_STORAGE)

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = ""

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val CameraCaptureType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> CameraCaptureType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                CameraCaptureType -> {
                    CameraCaptureViewHolder(layout)

                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is CameraCaptureViewHolder -> {
                    holder.initialize()
                    val result = checkPermission()
                    Timber.i("Result: $result")
                    surfaceView = holder.surfaceViewBase
                    if(result){
                        setupSurfaceHolder()
                    }
                }
            }
        }
    }

    private fun checkPermission(): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            val permissionsNotGranted = ArrayList<String>()
            for (permission in neededPermissions) {
                if (ContextCompat.checkSelfPermission(currentActivity!!, permission) != PackageManager.PERMISSION_GRANTED) {
                    permissionsNotGranted.add(permission)
                }
            }
            if (permissionsNotGranted.size > 0) {
                var shouldShowAlert = false
                for (permission in permissionsNotGranted) {
                    shouldShowAlert = ActivityCompat.shouldShowRequestPermissionRationale(
                        currentActivity!!, permission)
                }

                val arr = arrayOfNulls<String>(permissionsNotGranted.size)
                val permissions = permissionsNotGranted.toArray(arr)
                if (shouldShowAlert) {
                    showPermissionAlert(permissions)
                } else {
                    requestPermissions(permissions)
                }
                return false
            }
        }
        return true
    }

    private fun showPermissionAlert(permissions: Array<String?>) {

        alertManager.single(
            "Permission Required",
            "You must grant permission to access camera and external storage to run this application.",
            null,
            "Ok"){_, position ->
            if(position == 0 ){
                requestPermissions(permissions)
            }
        }
    }

    private fun requestPermissions(permissions: Array<String?>) {
        ActivityCompat.requestPermissions( currentActivity!!, permissions, REQUEST_CODE)
    }

    private fun setupSurfaceHolder() {
        surfaceView!!.visibility = View.VISIBLE
        surfaceHolder = surfaceView!!.holder
        surfaceHolder!!.addCallback(this)
        //= surfaceView!!.holder
        //surfaceCreated(surfaceHolder!!)
    }

    private fun startCamera() {
        Timber.i("Start camera")
        camera = Camera.open()
        camera!!.setDisplayOrientation(90)
        try {
            camera!!.setPreviewDisplay(surfaceHolder)
            camera!!.startPreview()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
        Timber.i("SurfaceHolder Creating : ${surfaceHolder.isCreating}")
        startCamera()
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
        resetCamera()
    }

    override fun onPictureTaken(p0: ByteArray?, p1: Camera?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun resetCamera() {
        if (surfaceHolder!!.surface == null) {
            // Return if preview surface does not exist
            return
        }

        // Stop if preview surface is already running.
        camera!!.stopPreview()
        try {
            // Set preview display
            camera!!.setPreviewDisplay(surfaceHolder)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Start the camera preview...
        camera!!.startPreview()
    }

    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        releaseCamera()
    }

    private fun releaseCamera() {
        camera!!.stopPreview()
        camera!!.release()
        camera = null
    }


    companion object {
        const val REQUEST_CODE = 100
    }
}



fun CameraView.renderUIO() {

    renderBodyTable()
}




