package com.ageone.naladonipartner.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.size
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladonipartner.Application.Coordinator.Router.DataFlow
import com.ageone.naladonipartner.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.External.Base.Flow.BaseFlow
import com.ageone.naladonipartner.External.Extensions.Activity.clearLightStatusBar
import com.ageone.naladonipartner.External.Icon
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.Modules.CameraInput.CameraInputView
import com.ageone.naladonipartner.Modules.CameraInput.CameraInputModel
import com.ageone.naladonipartner.Modules.CameraInput.CameraInputViewModel
import com.ageone.naladonipartner.R
import timber.log.Timber


fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()

    flow?.let { flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)
        flow.colorStatusBar = Color.parseColor("#F06F28")

        currentActivity?.clearLightStatusBar(Color.parseColor("#F06F28"),Color.WHITE)

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
        val module = CameraInputView(
            InitModuleUI(
            firstIcon = Icon(
                icon = R.drawable.ic_exit,
                size = 30,
                listener = {
                    Timber.i("Camera icon listener")
                }
            ))
        )

        module.viewModel.initialize(models.modelCameraInput) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (CameraInputViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}