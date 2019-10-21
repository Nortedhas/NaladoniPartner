package com.ageone.naladonipartner.Application.Coordinator.Router

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladonipartner.Application.Coordinator.Flow.setBottomNavigationVisible
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.Application.router
import com.ageone.naladonipartner.External.Extensions.Activity.hideKeyboard
import com.ageone.naladonipartner.External.Extensions.Function.guard
import timber.log.Timber
import yummypets.com.stevia.style

class Router {
    lateinit var layout: ConstraintLayout

    fun onBackPressed() {

        val current = currentFlow.guard {
            Timber.e("Current flow is null")
            return
        }

        if (current!!.stack.size > 1) {

            // MARK: in flow - pop module from flow
            Timber.i("pop module")

            current.pop()

        } else {

            // MARK: pop flow - change current flow to previous if it exists
            Timber.i("pop flow")

            val previousFlow = current.previousFlow.guard {
                Timber.e("Previous flow is null")
                return
            }

            //change current flow
            val flowToDelete = currentFlow
            currentFlow = previousFlow

            flowToDelete?.onFinish?.invoke()

            viewFlipperFlow.displayedChild = previousFlow!!.settingsCurrentFlow.indexOnFlipperFlow

            //correct visible bottom bar
            val isBottomBarVisible = previousFlow!!.settingsCurrentFlow.isBottomNavigationVisible
            setBottomNavigationVisible(isBottomBarVisible)
            currentFlow?.settingsCurrentFlow?.isBottomNavigationVisible = isBottomBarVisible

            currentActivity?.hideKeyboard()

        }

    }


    fun initialize() {
        // MARK: app's root layout
        layout = ConstraintLayout(currentActivity)

        router.layout.style {
            isFocusable = true
            isFocusableInTouchMode = true

        }

    }
}

// MARK: settings for correcting routing (pop function)

data class DataFlow(
    val indexOnFlipperFlow: Int = 0,
    var isBottomNavigationVisible: Boolean = false
)