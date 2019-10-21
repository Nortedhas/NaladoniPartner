package com.ageone.naladonipartner.Application.Coordinator.Flow

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import com.ageone.naladonipartner.Application.AppActivity
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladonipartner.Application.Coordinator.Router.TabBar.TabBar.bottomNavigation
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.Application.router
import com.ageone.naladonipartner.External.Base.ConstraintLayout.BaseConstraintLayout
import com.ageone.naladonipartner.External.Base.Flow.BaseFlow
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.Base.ViewFlipper.BaseViewFlipper
import com.ageone.naladonipartner.External.Extensions.Activity.setStatusBarColor
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.Models.User.user
import timber.log.Timber
import yummypets.com.stevia.*

var isBottomNavigationExist = true

class FlowCoordinator {
    fun setLaunchScreen() {

        router.initialize()
        renderUI()

        val launch = object: BaseModule(InitModuleUI(colorToolbar = Color.TRANSPARENT)){
        }
        launch.setBackgroundColor(Color.TRANSPARENT)

        viewFlipperFlow.subviews(
            launch
        )

        launch.toolbar
            .height(0)

    }

    fun start() {
        viewFlipperFlow.removeAllViews()
        when (LaunchInstructor.configure()) {
            LaunchInstructor.Main -> {
                Timber.i("Bottom run flow loading")
                runFlowLoading()
            }
            LaunchInstructor.Auth -> {
                runFlowAuth()
            }
        }
    }

    private fun renderUI() {
//        viewFlipperFlow = BaseViewFlipper()

        router.layout.removeAllViews()
        router.layout.subviews(
            viewFlipperFlow,
            bottomNavigation,
            blockConstraint
        )

        bottomNavigation.constrainBottomToBottomOf(router.layout)

        viewFlipperFlow
            .fillVertically()
            .fillHorizontally()
            .constrainBottomToTopOf(bottomNavigation)

        blockConstraint
            .fillVertically()
            .fillHorizontally()

        blockConstraint.removeAllViews()
        blockConstraint.subviews(
            circularProgress
        )

        circularProgress.centerInParent()

        blockConstraint.visibility = View.GONE
        circularProgress.visibility = View.GONE
    }

    val blockConstraint by lazy {
        val constraint = BaseConstraintLayout()
        constraint.setBackgroundColor(Color.argb(180, 0,0,0))
        constraint
    }

    val circularProgress by lazy {
        val circular = ProgressBar(currentActivity as Context)
        circular
    }

    object ViewFlipperFlowObject{
        var currentFlow: BaseFlow? = null

        val viewFlipperFlow: BaseViewFlipper by lazy {
            val flipper = BaseViewFlipper()
            flipper
        }

    }

}

fun setBottomNavigationVisible(visible: Boolean) = if (visible) {
    bottomNavigation.visibility = View.VISIBLE

} else {
    bottomNavigation.visibility = View.GONE

}

fun setStatusBarColor(color: Int) {
    currentActivity?.setStatusBarColor(color)
}

private enum class LaunchInstructor {

    Main, Auth;

    companion object {

        fun configure(isAutorized: Boolean = user.isAuthorized): LaunchInstructor {
            return when (isAutorized) {
                true -> Main
                false -> Auth
            }

        }

    }
}

