package com.ageone.naladonipartner.Modules.CameraInput.rows

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.External.Base.Button.BaseButton
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class CameraInputButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val buttonCamera by lazy {
        val button = BaseButton()
        button.backgroundColor = Color.parseColor("#F06F28")
        button.text = "Открыть камеру"
        button.cornerRadius = 8.dp
        button.textColor = Color.WHITE
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.initialize()
        button
    }

    init {

        renderUI()
    }

}

fun CameraInputButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonCamera
    )
    buttonCamera
        .constrainTopToTopOf(constraintLayout,48)
        .fillHorizontally()


}

fun CameraInputButtonViewHolder.initialize() {

}
