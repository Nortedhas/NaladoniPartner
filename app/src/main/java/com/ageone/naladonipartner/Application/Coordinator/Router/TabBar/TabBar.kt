package com.ageone.naladonipartner.Application.Coordinator.Router.TabBar

import android.graphics.Color
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.ageone.naladonipartner.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladonipartner.Application.Coordinator.Flow.isBottomNavigationExist
import com.ageone.naladonipartner.Application.Coordinator.Flow.setStatusBarColor
import com.ageone.naladonipartner.Application.Coordinator.Router.TabBar.Stack.flows
import com.ageone.naladonipartner.Application.Coordinator.Router.TabBar.Stack.items
import com.ageone.naladonipartner.R
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.External.Base.Flow.BaseFlow
import timber.log.Timber

object Stack {
    var flows = arrayListOf<BaseFlow>()
    var items = arrayListOf<AHBottomNavigationItem>()
}

object TabBar {

    fun createBottomNavigation() {
        isBottomNavigationExist = true

        Timber.i("Bottom nav: create bottom nav")

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected && position < flows.size) {
                viewFlipperFlow.displayedChild = position

                //if flow starts in the first time
                if (!flows[position].isStarted) {
                    flows[position].start()
                }

                //correct viewArrow current module
                currentFlow = flows[position]
                setStatusBarColor(flows[position].colorStatusBar)
            }
            true
        }

        createStackItem()
        setUpTabs()
    }

    val bottomNavigation by lazy {
        val bottomNavigation = AHBottomNavigation(currentActivity)
        bottomNavigation.setTitleTextSize(30f,30f)
        bottomNavigation.defaultBackgroundColor = Color.parseColor("#FEFEFE")//color
        bottomNavigation.isBehaviorTranslationEnabled = true
        bottomNavigation.accentColor = Color.parseColor("#F27D25")
        bottomNavigation.inactiveColor = Color.parseColor("#AFAFB4")
        bottomNavigation.isForceTint = true
        bottomNavigation.isTranslucentNavigationEnabled = false
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE
        bottomNavigation
    }

    private fun setUpTabs() {

        bottomNavigation.removeAllItems()
        for (item in items) {
            bottomNavigation.addItem(item)
        }

    }

    private fun createStackItem() {
        items = arrayListOf(
            AHBottomNavigationItem("", R.drawable.ic_camera),
            AHBottomNavigationItem("",R.drawable.ic_code)

        )

    }
}

