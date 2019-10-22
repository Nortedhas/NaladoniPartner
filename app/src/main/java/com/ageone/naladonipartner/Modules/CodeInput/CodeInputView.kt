package com.ageone.naladonipartner.Modules.CodeInput

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.R
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.External.RxBus.RxBus
import com.ageone.naladonipartner.External.RxBus.RxEvent
import com.ageone.naladonipartner.Modules.CodeInput.rows.CodeInputTextViewHolder
import com.ageone.naladonipartner.Modules.CodeInput.rows.initialize
import yummypets.com.stevia.*

class CodeInputView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = CodeInputViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = ""

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


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

        private val CodeInputTextType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> CodeInputTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                CodeInputTextType -> {
                    CodeInputTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is CodeInputTextViewHolder -> {
                    holder.initialize()
                }

            }

        }

    }

}

fun CodeInputView.renderUIO() {

    renderBodyTable()
}


