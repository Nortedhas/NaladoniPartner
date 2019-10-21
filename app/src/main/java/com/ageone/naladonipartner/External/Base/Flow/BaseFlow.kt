package com.ageone.naladonipartner.External.Base.Flow

import android.graphics.Color
import android.view.View
import androidx.core.view.contains
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.ageone.naladonipartner.Application.Coordinator.Flow.setBottomNavigationVisible
import com.ageone.naladonipartner.Application.Coordinator.Router.DataFlow
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.Base.Module.Module
import com.ageone.naladonipartner.External.Base.ViewFlipper.BaseViewFlipper
import com.ageone.naladonipartner.External.Extensions.Activity.hideKeyboard
import timber.log.Timber

abstract class BaseFlow: View(currentActivity){
    //modules in flow
    val stack = mutableListOf<Int>()

    //UserData for correct routing
    var settingsCurrentFlow: DataFlow = DataFlow()
    var previousFlow: BaseFlow? = null

    var onStart: (() -> Unit)? = null
    var onFinish: (() -> Unit)? = null

    var colorStatusBar = Color.TRANSPARENT

    //value for running the first module in flow (for navigation flows)
    var isStarted = false

    val viewFlipperModule by lazy {
        val viewFlipperModule = BaseViewFlipper()
        viewFlipperModule
    }

    init {

        onStart?.invoke()

    }

    fun onStarted(){
        currentFlow = this
        isStarted = true
    }

    fun push(module: Module?) {
        module?.let { module ->
            includeModule(module)
            //correct viewArrow module
            viewFlipperModule.displayedChild = stack.indexOf(module.idView)
            setBottomNavigationVisible(module.initModuleUI.isBottomNavigationVisible)
        }
    }

    fun pop() {
        if (stack.size > 1) {
            val currentModule = viewFlipperModule.currentView as Module
            deInitModule(currentModule)

            val isBottomBarVisible = (viewFlipperModule.currentView as Module).initModuleUI.isBottomNavigationVisible
            setBottomNavigationVisible(isBottomBarVisible)
            settingsCurrentFlow.isBottomNavigationVisible = isBottomBarVisible

            currentActivity?.hideKeyboard()
        }
    }

    fun popToRoot() {

    }

    fun deInitModule(module: Module?) {
        module?.let{ module ->
            
            if (stack.contains(module.idView)) {
                stack.remove(module.idView)
            }
            
            if (viewFlipperModule.contains(module.getView())) {
                viewFlipperModule.removeView(module.getView())
                //viewArrow show previous module
                viewFlipperModule.displayedChild = stack.lastIndex

            }
            module.onDeInit?.invoke()
            Timber.i("Module DeInit ${module.className()}")
        }
    }

    fun includeModule(module: Module?) {
        module?.let { module ->
            if (!stack.contains(module.idView)){
                stack.add(module.idView)
                viewFlipperModule.addView(module.getView())
            }
        }
    }

    abstract fun start()
}