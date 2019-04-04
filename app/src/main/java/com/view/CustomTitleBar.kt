package com.view


import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import com.emvl3kt.R
import kotlinx.android.synthetic.main.title_bar_layout.view.*

class CustomTitleBar (tmpcontext: Context,attributeSet: AttributeSet): ConstraintLayout(tmpcontext,attributeSet)
{

    var tmpcontext:Context=tmpcontext

    init {
        LayoutInflater.from(this.tmpcontext).inflate(R.layout.title_bar_layout, this)
    }

    fun backOnClick(onClickListener: OnClickListener)
    {
        img_back?.setOnClickListener(onClickListener)
    }
}
