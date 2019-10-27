package com.ageone.naladonipartner.Modules.Camera

import android.Manifest.permission.CAMERA
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.Typeface
import android.hardware.Camera
import android.os.Build
import android.os.Handler
import android.view.Gravity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.Base.TextView.BaseTextView
import com.ageone.naladonipartner.External.Base.View.BaseView
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.External.Libraries.Alert.alertManager
import com.ageone.naladonipartner.External.Libraries.Alert.single
import com.ageone.naladonipartner.Modules.Camera.CameraViewModel
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import timber.log.Timber
import yummypets.com.stevia.*
import java.io.IOException

class CameraView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI), ZXingScannerView.ResultHandler{


    val viewModel = CameraViewModel()

    val qrCodeScanner by lazy {
        val qrScanner = ZXingScannerView(currentActivity)
        qrScanner
    }

    val transparentView by lazy {
        val view = BaseView()
        view.alpha = 0.3F
        view.backgroundColor = Color.BLACK
        view.initialize()
        view
    }

    val textViewCamera by lazy {
        val textView = BaseTextView()
        textView.textSize = 23F
        textView.textColor = Color.WHITE
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.backgroundColor = Color.TRANSPARENT
        textView.text = "Отсканируйте QR-код"
        textView
    }

    val textViewDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 15F
        textView.textColor = Color.WHITE
        textView.backgroundColor = Color.TRANSPARENT
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.text = "Наведите камеру на экран\nклиента и считайте код"
        textView
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundColor(Color.WHITE)

        toolbar.title = "Вкусная шаверма"
        toolbar.setBackgroundColor(Color.parseColor("#F06F28"))
        toolbar.textColor = Color.WHITE

        renderToolbar()

        renderUIO()

        setScannerProperties()
        openCamera()

        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    private fun setScannerProperties() {
        qrCodeScanner.setFormats(listOf(BarcodeFormat.QR_CODE))
        qrCodeScanner.setAutoFocus(true)
        }



    companion object {
        private const val HUAWEI = "huawei"
        private const val MY_CAMERA_REQUEST_CODE = 6515
    }

    override fun handleResult(p0: Result?) {
        if(p0 != null){
            alertManager.single("Наладони",p0.text,null,"Понятно"){_,_-> openCamera() }
            Timber.i("QR_CODE : ${p0.text}")

        }
    }

    private fun openCamera() {
        qrCodeScanner.startCamera()
        qrCodeScanner.setResultHandler(this)
    }

    private fun resumeCamera() {
        Handler().postDelayed({ qrCodeScanner.resumeCameraPreview(this) }, 500)
    }


}

fun CameraView.renderUIO() {
    innerContent.subviews(
        qrCodeScanner,
        transparentView,
        textViewCamera,
        textViewDescription
    )

    qrCodeScanner
        .fillHorizontally()
        .fillVertically()


    transparentView
        .constrainTopToTopOf(innerContent)
        .fillHorizontally()
        .width(matchParent)
        .height(112)

    textViewCamera
        .constrainTopToTopOf(innerContent,20)
        .constrainCenterXToCenterXOf(innerContent)

    textViewDescription
        .constrainTopToBottomOf(textViewCamera,8)
        .constrainCenterXToCenterXOf(textViewCamera)
}





