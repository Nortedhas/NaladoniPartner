package com.ageone.naladonipartner.Modules.Start

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.Modules.Start.rows.StartImageViewHolder
import com.ageone.naladonipartner.Modules.Start.rows.initialize
import com.ageone.naladonipartner.R
import yummypets.com.stevia.*

class StartView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = StartViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.loading)

        toolbar.title = ""
        toolbar.setBackgroundColor(Color.TRANSPARENT)

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val StartImageType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> StartImageType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                StartImageType -> {
                    StartImageViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is StartImageViewHolder -> {
                    holder.initialize(R.drawable.ic_start)
                }
            }
        }
    }
}

fun StartView.renderUIO() {
    renderBodyTable()
}


