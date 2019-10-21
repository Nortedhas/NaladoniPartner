package com.ageone.naladonipartner.Application.Coordinator.Flow.Stack


import androidx.core.view.size
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladonipartner.Application.Coordinator.Router.DataFlow
import com.ageone.naladonipartner.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladonipartner.External.Base.Flow.BaseFlow
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.Modules.CameraInput.CameraInputView
import com.ageone.naladonipartner.Modules.CameraInput.CameraInputModel
import com.ageone.naladonipartner.Modules.CameraInput.CameraInputViewModel


fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()

    flow?.let { flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()


}

class FlowMain : BaseFlow() {

    private var models = FlowMainModels()

    override fun start() {
        onStarted()
        runModuleCameraInput()
    }

    inner class FlowMainModels {
        var modelCameraInput = CameraInputModel()
    }

    fun runModuleCameraInput() {
        val module = CameraInputView()

        module.viewModel.initialize(models.modelCameraInput) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (CameraInputViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}