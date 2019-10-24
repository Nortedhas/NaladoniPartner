package com.ageone.naladonipartner.Modules.Auth.rows

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.External.Base.Button.BaseButton
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class AuthButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val buttonAuth by lazy {
        val button = BaseButton()
        button.backgroundColor = Color.parseColor("#F06F28")
        button.text = "Войти в приложение"
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

fun AuthButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonAuth
    )

    buttonAuth
        .constrainTopToTopOf(constraintLayout,33)
        .fillHorizontally()
        .height(50F)
}

fun AuthButtonViewHolder.initialize() {

}
