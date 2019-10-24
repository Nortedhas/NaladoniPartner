package com.ageone.naladonipartner.Modules.Start.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladonipartner.External.Base.ImageView.BaseImageView
import com.ageone.naladonipartner.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladonipartner.External.Base.TextView.BaseTextView
import com.ageone.naladonipartner.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.*

class StartImageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewStart by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F.dp
        textView.textColor = Color.WHITE
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.text = "NALADONI"
        textView
    }

    val textViewDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 10F.dp
        textView.textColor = Color.WHITE
        textView.text = "Все акции твоего города"
        textView
    }
    init {

        renderUI()
    }
}

fun StartImageViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewStart,
        textViewName,
        textViewDescription
    )

    imageViewStart
        .constrainTopToTopOf(constraintLayout,210)
        .constrainCenterXToCenterXOf(constraintLayout)
        .width(102)
        .height(119)

    textViewName
        .constrainTopToBottomOf(imageViewStart,11)
        .constrainCenterXToCenterXOf(imageViewStart)

    textViewDescription
        .constrainTopToBottomOf(textViewName)
        .constrainCenterXToCenterXOf(imageViewStart)
}

fun StartImageViewHolder.initialize(image: Int) {
    addImageFromGlide(imageViewStart,image,0)
}
