package com.ageone.naladonipartner.External.Utils

import android.graphics.Color
import com.ageone.naladonipartner.Application.utils

object Tools {
    fun hex(hex: String): Int {
        return Color.parseColor("#$hex")
    }

    fun getClassName(name: String): String {
        return name.split("{")[0]
    }

    fun getActualSizeFromDes(size: Int) = utils.variable.displayWidth * (size / utils.variable.widthDisplayDesign)
}

object Variable {
    var statusBarHeight = 0
    var displayWidth = 0
    var displayHeight = 0
    var actionBarHeight = 0
    var token = ""

    val widthDisplayDesign = 375F
}