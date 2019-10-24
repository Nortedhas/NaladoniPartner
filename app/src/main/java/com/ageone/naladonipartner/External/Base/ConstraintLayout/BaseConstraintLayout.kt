package com.ageone.naladonipartner.External.Base.ConstraintLayout

import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.Application.utils
import com.ageone.naladonipartner.External.Base.Button.BaseButton
import yummypets.com.stevia.constrainBottomToBottomOf

class BaseConstraintLayout: ConstraintLayout(currentActivity) {

}
fun BaseConstraintLayout.setButtonAboveKeyboard(button: BaseButton) {
    val rectLayout = Rect()
    getWindowVisibleDisplayFrame(rectLayout)

    var usableRect = rectLayout.bottom - rectLayout.top
    val layoutHeight = usableRect - utils.variable.statusBarHeight

    viewTreeObserver.addOnGlobalLayoutListener {

        val rect = Rect()
        getWindowVisibleDisplayFrame(rect)

        var displayHeight = rootView.height
        var usableHeight = rect.bottom - rect.top
        var bottomButton = displayHeight - layoutHeight

        var keyboardHeight = displayHeight - usableHeight - bottomButton
        var coff: Float = keyboardHeight.toFloat() / layoutHeight
        var heightInDp = utils.variable.displayHeight
        var margin = (heightInDp * coff) - 8

        if (margin > 50) {
            button.constrainBottomToBottomOf(this, margin.toInt())
        } else if (margin < 50) {
            button.constrainBottomToBottomOf(this)
        }
    }

}

fun BaseConstraintLayout.dismissFocus(view: EditText?) {

    viewTreeObserver.addOnGlobalLayoutListener {
        val rect = Rect()
        val height: Float = rootView.height.toFloat()

        getWindowVisibleDisplayFrame(rect)

        var heightDiff =
            ((((height - rect.height()) / height) * utils.variable.displayHeight).toInt())

        if (heightDiff < 50) {
            view?.clearFocus()
            view?.isFocusableInTouchMode = true
        }
    }
}




fun BaseConstraintLayout.PxtoDp(px: Float, context: Context): Float{
    return px / context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
}