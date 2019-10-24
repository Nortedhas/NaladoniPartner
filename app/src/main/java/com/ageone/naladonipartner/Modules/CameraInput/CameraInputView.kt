package com.ageone.naladonipartner.Modules.CameraInput

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.External.RxBus.RxBus
import com.ageone.naladonipartner.External.RxBus.RxEvent
import com.ageone.naladonipartner.Modules.CameraInput.rows.CameraInputButtonViewHolder
import com.ageone.naladonipartner.Modules.CameraInput.rows.CameraInputTextViewHolder
import com.ageone.naladonipartner.Modules.CameraInput.rows.initialize
import yummypets.com.stevia.*

class CameraInputView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = CameraInputViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundColor(Color.WHITE)

        toolbar.title = "Вкусная шаверма"
        toolbar.setBackgroundColor(Color.parseColor("#F06F28"))
        toolbar.textColor = Color.WHITE

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

        private val CameraInputTextType = 0
        private val CameraInputButtonType = 1

        override fun getItemCount() = 2//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> CameraInputTextType
            1 -> CameraInputButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                CameraInputTextType -> {
                    CameraInputTextViewHolder(layout)
                }
                CameraInputButtonType -> {
                    CameraInputButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is CameraInputTextViewHolder -> {
                    holder.initialize()
                }
                is CameraInputButtonViewHolder -> {
                    holder.initialize()
                    holder.buttonCamera.setOnClickListener {
                        rootModule.emitEvent?.invoke(CameraInputViewModel.EventType.OnCameraPressed.name)
                    }
                }
            }
        }
    }
}

fun CameraInputView.renderUIO() {
    renderBodyTable()
}


