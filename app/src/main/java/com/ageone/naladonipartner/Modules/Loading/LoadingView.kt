package com.ageone.naladonipartner.Modules

import com.ageone.naladonipartner.R
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.InitModuleUI
import timber.log.Timber
import yummypets.com.stevia.subviews

class LoadingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = LoadingViewModel()

    init {
        setBackgroundResource(R.drawable.base_background)

        innerContent.subviews(
        )

        Timber.i("Bottom init loading view")

    }

    fun loading(){
        viewModel.startLoading {
            emitEvent?.invoke(LoadingViewModel.EventType.onFinish.name)
        }

    }



}
