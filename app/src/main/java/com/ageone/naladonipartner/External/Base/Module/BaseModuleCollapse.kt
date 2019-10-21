package com.ageone.naladonipartner.External.Base.Module

import android.content.Context
import android.view.View
import android.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.updatePadding
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.ageone.naladonipartner.External.Base.Toolbar.BaseToolbar
import com.ageone.naladonipartner.Application.currentActivity
import com.ageone.naladonipartner.Application.utils
import com.ageone.naladonipartner.External.Base.ConstraintLayout.BaseConstraintLayout
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseRecyclerView
import com.ageone.naladonipartner.External.Extensions.Activity.hideKeyboard
import com.ageone.naladonipartner.External.InitModuleUI
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import yummypets.com.stevia.*


open class BaseModuleCollapse(override var initModuleUI: InitModuleUI = InitModuleUI()): CoordinatorLayout(currentActivity as Context), Module {
    override fun getView(): View = this

    override val idView: Int
        get() = id


    val appBarLayout by lazy {
        val appBarLayout = AppBarLayout(currentActivity)
        appBarLayout
    }

    val collapsingToolbarLayout by lazy {
        val collapsingToolbarLayout = CollapsingToolbarLayout(currentActivity)
        collapsingToolbarLayout
    }

    val nestedScrollView by lazy {
        val nestedScrollView = NestedScrollView(currentActivity as Context)
        nestedScrollView
    }

    private val content by lazy {
        val innerContent = BaseConstraintLayout()
        innerContent.setPadding(0, utils.variable.statusBarHeight, 0, 0)
        innerContent
    }

    val toolbar by lazy {
        val toolBar = Toolbar(currentActivity)

        toolBar
            .setBackgroundColor(initModuleUI.colorToolbar)

        toolBar.title = "all"
        if (initModuleUI.isToolbarHidden) {
            toolBar.visibility = View.GONE
        }

        toolBar
    }

    val viewManagerBodyTable by lazy {
        val viewManager = LinearLayoutManager(currentActivity)
        viewManager
    }
    
    val bodyTable by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView.layoutManager = viewManagerBodyTable
        recyclerView
    }

    override var onDeInit: (() -> Unit)? = null
    override var emitEvent: ((String) -> Unit)? = null

    init {
        id = View.generateViewId()
        renderUI()

        currentActivity?.hideKeyboard()
        Timber.i("${this.className()} Init ")
    }

    var compositeDisposable = CompositeDisposable()

    fun unBind() {
        compositeDisposable.dispose()
    }

    fun renderUI() {
        width(matchParent)
        height(matchParent)

        subviews(
            nestedScrollView.subviews(
                content
            ),
            appBarLayout.subviews(
                collapsingToolbarLayout.subviews(
                    toolbar
                )
            )
        )

        appBarLayout
            .height(100)
            .width(matchParent)

        collapsingToolbarLayout
            .width(matchParent)
            .height(matchParent)

        collapsingToolbarLayout.apply {
            expandedTitleMarginStart = 48.dp
            expandedTitleMarginEnd = 64.dp
        }

        collapsingToolbarLayout.setScrollFlags()


        toolbar.setCollapseMode()
        toolbar
            .fillHorizontally()
            .height(utils.variable.actionBarHeight)

        nestedScrollView.setScrollinViewBehaviour()

        nestedScrollView
            .fillHorizontally()
            .fillVertically()

        content
            .fillHorizontally()
            .fillVertically()

    }

    private fun CollapsingToolbarLayout.setScrollFlags() {
        val params = layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags =
            AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
    }

    private fun NestedScrollView.setScrollinViewBehaviour() {
        val params = layoutParams
        val newParams: LayoutParams
        newParams = if (params is LayoutParams) {
            params
        } else {
            LayoutParams(params)
        }
        newParams.behavior = AppBarLayout.ScrollingViewBehavior()
        layoutParams = newParams
        requestLayout()
    }

    fun renderToolbar() {
//        toolbar.initialize()
    }

    fun renderBodyTable() {
        content.subviews(
            bodyTable
        )

        bodyTable
            .fillHorizontally(8)//TODO: change!
            .fillVertically()
            .constrainTopToTopOf(content)
            .updatePadding(bottom = 24.dp)

        bodyTable
            .clipToPadding = false

    }

    fun Toolbar.setCollapseMode(){
        val params = layoutParams
        val newParams: CollapsingToolbarLayout.LayoutParams
        newParams = if (params is CollapsingToolbarLayout.LayoutParams) {
            params
        } else {
            CollapsingToolbarLayout.LayoutParams(params)
        }
        newParams.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
        layoutParams = newParams
        requestLayout()
    }

    open fun reload() {
        bodyTable.adapter?.notifyDataSetChanged()
    }

    override fun className(): String {
        return utils.tools.getClassName(this.toString())
    }
}
