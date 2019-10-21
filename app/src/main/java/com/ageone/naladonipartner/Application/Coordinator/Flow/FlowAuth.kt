package com.ageone.naladonipartner.Application.Coordinator.Flow

import androidx.core.view.size
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladonipartner.Application.Coordinator.Flow.Regular.runFlowFAQ
import com.ageone.naladonipartner.Application.Coordinator.Router.DataFlow
import com.ageone.naladonipartner.Application.coordinator
import com.ageone.naladonipartner.External.Base.Flow.BaseFlow
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.Modules.Auth.AuthModel
import com.ageone.naladonipartner.Modules.Auth.AuthView
import com.ageone.naladonipartner.Modules.Auth.AuthViewModel
import com.ageone.naladonipartner.Modules.City.CityModel
import com.ageone.naladonipartner.Modules.City.CityView
import com.ageone.naladonipartner.Modules.City.CityViewModel
import com.ageone.naladonipartner.Modules.LoadingScreen.LoadingScreenModel
import com.ageone.naladonipartner.Modules.LoadingScreen.LoadingScreenView
import com.ageone.naladonipartner.Modules.LoadingScreen.LoadingScreenViewModel
import com.ageone.naladonipartner.Modules.SMS.SMSView
import com.ageone.naladonipartner.Modules.SMSModel
import com.ageone.naladonipartner.Modules.SMSViewModel

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
        var modelStart = LoadingScreenModel()
        var modelAuth = AuthModel()
        var modelSMS = SMSModel()
        var modelSelectCity = CityModel()
    }

    fun runModuleStart() {
        val module = LoadingScreenView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))
        module.viewModel.initialize(models.modelStart) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(LoadingScreenViewModel.EventType.valueOf(event)) {
                LoadingScreenViewModel.EventType. OnRegistrationPhonePressed -> {
                    runModuleAuth()
                }

            }
        }
        push(module)
    }
    fun runModuleAuth() {
        val module = AuthView(InitModuleUI(
            isBottomNavigationVisible = false
        ))
        module.viewModel.initialize(models.modelAuth) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(AuthViewModel.EventType.valueOf(event)) {
                AuthViewModel.EventType.OnAuthPressed -> {

                    models.modelSMS.inputName = models.modelAuth.inputName
                    models.modelSMS.inputPhone = models.modelAuth.inputPhone

                    runModuleSMS()
                }
            }
        }
        push(module)
    }

    fun runModuleSMS() {
        val module = SMSView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))
        module.viewModel.initialize(models.modelSMS) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (SMSViewModel.EventType.valueOf(event)) {
                SMSViewModel.EventType.OnAcceptCode -> {
                    runModuleCity()
                }
                SMSViewModel.EventType.onTimerPresed -> {
                    runModuleAuth()

                }
            }
        }
        push(module)
    }


    fun runModuleCity() {
        val module = CityView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))
        module.viewModel.initialize(models.modelSelectCity) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (CityViewModel.EventType.valueOf(event)) {
                CityViewModel.EventType.OnAcceptCode -> {
                    coordinator.runFlowFAQ()
                }
            }
        }
        push(module)
    }
}