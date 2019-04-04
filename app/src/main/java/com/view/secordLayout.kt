package com.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.emvl3kt.R

class secordLayout:LinearLayout {
    constructor(context:Context):super(context)
    {
        LayoutInflater.from(context).inflate(R.layout.secordlayout,this)
    }
}