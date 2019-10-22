package com.ageone.naladonipartner.Application.Coordinator.Flow.Stack


import android.graphics.Color
import androidx.core.view.size
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladonipartner.Application.Coordinator.Router.DataFlow
import com.ageone.naladonipartner.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladonipartner.External.Base.Flow.BaseFlow
import com.ageone.naladonipartner.External.Icon
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.Modules.CodeInput.CodeInputView
import com.ageone.naladonipartner.Modules.CodeInput.CodeInputModel
import com.ageone.naladonipartner.Modules.CodeInput.CodeInputViewModel
import com.ageone.naladonipartner.R
import timber.log.Timber

fun FlowCoordinator.runFlowCode() {

    var flow: FlowCode? = FlowCode()

    flow?.let { flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)
        flow.colorStatusBar = Color.parseColor("#F06F28")

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()


}

class FlowCode : BaseFlow() {

    private var models = FlowCodeModels()

    override fun start() {
        onStarted()
        runModuleCodeInput()
    }

    inner class FlowCodeModels {
        var modelCodeInput = CodeInputModel()
    }

    fun runModuleCodeInput() {
        val module = CodeInputView(
            InitModuleUI(
                firstIcon = Icon(
                    icon = R.drawable.ic_exit,
                    size = 30,
                    listener = {
                        Timber.i("Code icon listener")
                    }
                ))
        )

        module.viewModel.initialize(models.modelCodeInput) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (CodeInputViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}