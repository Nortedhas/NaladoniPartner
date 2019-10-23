package com.ageone.naladonipartner.Modules.Camera.rows

import android.graphics.SurfaceTexture
import android.hardware.Camera
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.TextureView
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import timber.log.Timber
import yummypets.com.stevia.*
import java.io.IOException

class CameraCaptureViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout)/*,SurfaceHolder.Callback, Camera.PictureCallback  */{


    private var surfaceHolder: SurfaceHolder? = null

    private var camera: Camera? = null
    //private var surfaceView: SurfaceView? = null


    val surfaceViewBase by lazy {
        val surface = SurfaceView(currentActivity)
        surface
    }


    init {

        renderUI()
    }

//    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
//        Timber.i("SurfaceHolder Creating ")
//        startCamera(surfaceHolder)
//    }
//
//
//    fun setupSurfaceHolder() {
//       /* surfaceView = surfaceViewBase
//        surfaceView?.visibility = View.VISIBLE*/
//        surfaceHolder = surfaceViewBase!!.holder
//        surfaceCreated(surfaceHolder!!)
//        surfaceHolder!!.addCallback(this)
//    }
//
//    private fun startCamera(surfaceHolder: SurfaceHolder) {
//        // Timber.i("Start camera")
//        camera = Camera.open()
//        camera!!.setDisplayOrientation(90)
//        try {
//            camera!!.setPreviewDisplay(surfaceHolder)
//            camera!!.startPreview()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//    }
//
//    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {
//        resetCamera()
//    }
//
//    override fun onPictureTaken(p0: ByteArray?, p1: Camera?) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    private fun resetCamera() {
//        if (surfaceViewBase.holder!!.surface == null) {
//            // Return if preview surface does not exist
//            return
//        }
//
//        // Stop if preview surface is already running.
//        camera!!.stopPreview()
//        try {
//            // Set preview display
//            camera!!.setPreviewDisplay(surfaceViewBase.holder)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//
//        // Start the camera preview...
//        camera!!.startPreview()
//    }
//
//    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
//        releaseCamera()
//    }
//
//    private fun releaseCamera() {
//        camera!!.stopPreview()
//        camera!!.release()
//        camera = null
//    }
//
//

}

fun CameraCaptureViewHolder.renderUI() {
    constraintLayout.subviews(
            surfaceViewBase
    )



    surfaceViewBase
        .width(matchParent)
        .height(matchParent)
        .fillHorizontally()
        .fillVertically()

}
fun CameraCaptureViewHolder.initialize() {

}
