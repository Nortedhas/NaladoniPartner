package com.ageone.naladonipartner.Modules.Start

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.External.Base.ImageView.BaseImageView
import com.ageone.naladonipartner.External.Base.Module.BaseModule
import com.ageone.naladonipartner.External.Base.ProgressBar.BaseProgressBar
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.Base.TextView.BaseTextView
import com.ageone.naladonipartner.External.InitModuleUI
import com.ageone.naladonipartner.Modules.Start.rows.StartImageViewHolder
import com.ageone.naladonipartner.Modules.Start.rows.initialize
import com.ageone.naladonipartner.R
import yummypets.com.stevia.*

class StartView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = StartViewModel()



    val textViewHello by lazy {
        val textViewHello = BaseTextView()
        textViewHello.gravity = Gravity.CENTER
        textViewHello.typeface = Typeface.DEFAULT
        textViewHello.textSize = 19F
        textViewHello.textColor = Color.WHITE
        textViewHello.setBackgroundColor(Color.TRANSPARENT)
        textViewHello.text = "Все акции твоего города   "
        textViewHello
    }

    val progressBar by lazy {
        val progressBar = BaseProgressBar()
        progressBar.color = Color.WHITE
        progressBar.initialize()
        progressBar
    }

    val textViewName by lazy {
        val textViewHello = BaseTextView()
        textViewHello.gravity = Gravity.CENTER
        textViewHello.typeface = Typeface.DEFAULT_BOLD
        textViewHello.textSize = 29F
        textViewHello.textColor = Color.WHITE
        textViewHello.setBackgroundColor(Color.TRANSPARENT)
        textViewHello.text = "NALADONI"
        textViewHello
    }

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView
            .width(96)
            .height(95)
        imageView.setImageResource(R.drawable.ic_start)
        imageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        imageView
    }


    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.loading)

        renderToolbar()

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

}

fun StartView.renderUIO() {
    innerContent.subviews(
        imageView,
        textViewHello,
        textViewName,
        progressBar
    )

    textViewName
        .constrainBottomToBottomOf(innerContent, 300)
        .fillHorizontally(35)

    textViewHello
        .constrainTopToBottomOf(textViewName, 5)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)

    imageView
        .constrainBottomToTopOf(textViewName, 5)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)
    progressBar
        .constrainTopToBottomOf(textViewHello, 100)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)

    var timer = object : CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            millisUntilFinished / 1000

        }

        override fun onFinish() {
            emitEvent?.invoke(StartViewModel.EventType.OnCodePressed.name)
        }
    }
    timer.start()

}


