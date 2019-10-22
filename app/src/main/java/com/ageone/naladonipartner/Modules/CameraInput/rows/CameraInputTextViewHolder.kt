package com.ageone.naladonipartner.Modules.CameraInput.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class CameraInputTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewEnter by lazy {
        val textView = BaseTextView()
        textView.textSize = 23F
        textView.textColor = Color.parseColor("#333333")
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.backgroundColor = Color.TRANSPARENT
        textView.text = "Отсканируйте QR-код"
        textView
    }

    val textViewDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#333333")
        textView.backgroundColor = Color.TRANSPARENT
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.text = "Наведите камеру на экран клиента\nи считайте код"
        textView
    }


    init {
        renderUI()
    }
}

fun CameraInputTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewEnter,
        textViewDescription
    )

    textViewEnter
        .constrainTopToTopOf(constraintLayout,40)
        .constrainCenterXToCenterXOf(constraintLayout)

    textViewDescription
        .constrainTopToBottomOf(textViewEnter,16)
        .constrainCenterXToCenterXOf(textViewEnter)

}

fun CameraInputTextViewHolder.initialize() {

}
