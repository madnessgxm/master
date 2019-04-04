package com.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout
import android.widget.ScrollView
import com.selfview.selfTextView
import java.util.jar.Attributes


class myScollView: ScrollView {
    var tag:String = "myScollView"
    var firstx:Int = 0
    var firsty:Int = 0

    constructor(context:Context):super(context)
    {
        setWillNotDraw(false)
    }

    constructor(context: Context,att: AttributeSet):super(context,att)
    {
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if(getChildAt(0) is LinearLayout) {
                Log.d(tag, " myScollView onDraw 1")
        }

    }
}