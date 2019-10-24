package com.ageone.naladonipartner.Modules.CodeInput.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.naladonipartner.External.Base.EditText.limitLength
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.naladonipartner.External.Base.TextInputLayout.InputEditTextType
import yummypets.com.stevia.*

class CodeInputTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputCode by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params

        textInput.boxStrokeColor = Color.parseColor("#C1C1C1")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))
        textInput.defineType(InputEditTextType.NUMERIC)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 20F
            editText.maxLines = 1
            editText.limitLength(9)
            editText.setSingleLine(true)
        }
        textInput
    }

    init {
        renderUI()
    }
}

fun CodeInputTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputCode
    )

    textInputCode
        .constrainTopToTopOf(constraintLayout,32)
        .fillHorizontally()
}

fun CodeInputTextInputViewHolder.initialize(hint: String) {
    textInputCode.hint = hint
}
