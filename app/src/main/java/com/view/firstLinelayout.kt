package com.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.emvl3kt.R

class firstLinelayout :LinearLayout {
   constructor(context:Context) : super(context){
     var view= LayoutInflater.from(context).inflate(R.layout.firstlayout,this)
   }
}