package com.ageone.naladonipartner.Application.Coordinator.Flow

import android.os.Handler
import androidx.core.view.size
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
//import com.ageone.naladonipartner.Application.Coordinator.Flow.Regular.runFlowFAQ
import com.ageone.naladonipartner.Application.Coordinator.Router.DataFlow
import com.ageone.naladonipartner.Application.coordinator
import com.ageone.naladonipartner.External.Base.Flow.BaseFlow
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.Modules.Auth.AuthModel
import com.ageone.naladonipartner.Modules.Auth.AuthView
import com.ageone.naladonipartner.Modules.Start.StartModel
import com.ageone.naladonipartner.Modules.Start.StartView


fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()

}

class FlowAuth: BaseFlow() {

    private var models = FlowAuthModels()

    override fun start() {
        onStarted()
        runModuleStart()
    }

    inner class FlowAuthModels {
        val modelStart = StartModel()
        val modelAuth = AuthModel()
    }

    fun runModuleStart() {
        val module = StartView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))
        module.viewModel.initialize(models.modelStart) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        Handler().postDelayed({
            runModuleAuth()
        },4000)

        push(module)
    }

    fun runModuleAuth(){
        val module = AuthView(
            InitModuleUI(
                isBottomNavigationVisible = false
            ))
        module.viewModel.initialize(models.modelAuth) { module.reload() }
        settingsCurrentFlow.isBottomNavigationVisible = false

        push(module)
    }



}