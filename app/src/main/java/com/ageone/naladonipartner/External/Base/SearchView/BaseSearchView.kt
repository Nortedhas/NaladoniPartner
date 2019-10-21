package com.ageone.naladonipartner.External.Base.SearchView

import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.ageone.naladonipartner.Application.currentActivity


class BaseSearchView: SearchView(currentActivity) {
    var isAlwaysExpand: Boolean? = null

    var color: Int? = null

    fun initialize() {
        color?.let { color ->
            val editText =
                findViewById<EditText?>(androidx.appcompat.R.id.search_src_text)

            val searchCloseIcon =
                findViewById<ImageView?>(androidx.appcompat.R.id.search_close_btn)
            val searchInnerIcon =
                findViewById<ImageView?>(androidx.appcompat.R.id.search_mag_icon)

            editText?.setTextColor(color)
            editText?.setHintTextColor(color)

            val v = findViewById<View?>(androidx.appcompat.R.id.search_plate)
            v?.setBackgroundColor(Color.TRANSPARENT)

            searchCloseIcon?.setColorFilter(color)
            searchInnerIcon?.setColorFilter(color)
        }

        isAlwaysExpand?.let { isAlwaysExpand ->
            setIconifiedByDefault(!isAlwaysExpand)
        }

    }
}