package com.ageone.naladonipartner.External.Base.RadioButton

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.Gravity
import androidx.appcompat.widget.AppCompatRadioButton
import com.ageone.naladonipartner.R
import com.ageone.naladonipartner.Application.currentActivity

class BaseRadioButton: AppCompatRadioButton(currentActivity) {

    fun setButtonBottom(){
        buttonDrawable = null
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, getButton())
        gravity = Gravity.CENTER
    }

}

private fun getButton(): Drawable? {
    val a =
        currentActivity?.theme?.obtainStyledAttributes(
            R.style.AppTheme,
            intArrayOf(android.R.attr.listChoiceIndicatorSingle)
        )
    val attributeResourceId = a?.getResourceId(0, 0)
    val drawable = currentActivity?.resources?.getDrawable(attributeResourceId ?: 0)
    drawable?.setTintList(getStateList(Color.WHITE, Color.rgb(0x70,0x7A,0xBA)))

    return drawable
}

private fun getStateList(uncheckedColor: Int, checkedColor: Int) = ColorStateList(
    arrayOf(
        intArrayOf(-android.R.attr.state_checked), // unchecked
        intArrayOf(android.R.attr.state_checked)  // checked
    ),
    intArrayOf(uncheckedColor, checkedColor)
)