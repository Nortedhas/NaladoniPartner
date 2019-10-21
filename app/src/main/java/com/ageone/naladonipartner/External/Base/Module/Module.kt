package com.ageone.naladonipartner.External.Base.Module

import android.view.View
import com.ageone.naladonipartner.External.InitModuleUI

interface Module {
    val idView: Int
    var initModuleUI: InitModuleUI

    var onDeInit: (() -> Unit)?
    var emitEvent: ((String) -> Unit)?

    fun className(): String
    fun getView(): View
}