package com.ageone.naladonipartner.Modules.Auth.rows

import android.graphics.Color
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.Base.TextView.BaseTextView
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.*

class AuthTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewAuth by lazy {
        val textView = BaseTextView()
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#333333")
        textView.text = "Введите в строку ниже код-идентификатор,\nкоторый передал вам администратор\nприложения "
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView
    }

    init {
        renderUI()
    }
}

fun AuthTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewAuth
    )
    textViewAuth
        .constrainTopToTopOf(constraintLayout,80)
        .constrainCenterXToCenterXOf(constraintLayout)
}

fun AuthTextViewHolder.initialize() {

}
