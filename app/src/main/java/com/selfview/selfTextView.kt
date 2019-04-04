package com.selfview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.emvl3kt.R
import kotlin.math.log

class selfTextView :View {
    val paint=Paint(Color.RED)
    var txtstr :String=""
    val tag:String = "selftxt"
    constructor(context:Context):super(context)
    {
        Log.d(tag,"1")
    }
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)
    {
        Log.d(tag,"2")
        var typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.myScollView)
        txtstr = typedArray.getString(R.styleable.myScollView_txt)
        setWillNotDraw(false)
        paint.textSize = 20.0.toFloat()
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 1.0.toFloat()
    }


    var bcWidth:Int=0
    var bcHeigth:Int = 0

    open fun setwidthheight(width:Int,heigth:Int)
     {
            bcWidth = width
            bcHeigth = heigth
     }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if(bcWidth !=0 && bcHeigth!=0)
        {
            setMeasuredDimension(bcWidth,bcHeigth)

        }else {

            //处理warp_context 的支持
            var widthMeasuremode = MeasureSpec.getMode(widthMeasureSpec)
            var widthMeasuresize = MeasureSpec.getSize(widthMeasureSpec)
            var heightMeasuremode = MeasureSpec.getMode(heightMeasureSpec)
            var heightMeasuresize = MeasureSpec.getSize(heightMeasureSpec)

            Log.d(tag, "onMeasure size " + widthMeasuresize.toString() + " " + heightMeasuresize.toString())

            if (widthMeasuremode == MeasureSpec.AT_MOST && heightMeasuremode == MeasureSpec.AT_MOST) {
                Log.d(tag, "onMeasure 1")
                setMeasuredDimension(200, 200)
            } else if (widthMeasuremode == MeasureSpec.AT_MOST) {
                Log.d(tag, "onMeasure 2")
                setMeasuredDimension(200, heightMeasuresize)
            } else if (heightMeasuremode == MeasureSpec.AT_MOST) {
                Log.d(tag, "onMeasure 3")
                setMeasuredDimension(widthMeasuresize, 200)
            } else {
                Log.d(tag, "onMeasure 4")
            }
        }

    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        Log.d(tag,"dispatchDraw")
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(tag,"onDraw")
        var widthx = width - paddingLeft - paddingRight
        var heighty = height - paddingTop - paddingBottom
        if(txtstr.isEmpty())
        {
            txtstr = "hello word"
        }
        Log.d(tag,txtstr)
        Log.d(tag,widthx.toString())
        Log.d(tag,heighty.toString())
        Log.d(tag,paddingLeft.toString())
        Log.d(tag,paddingTop.toString())
        canvas!!.drawText(txtstr,paddingLeft.toFloat(),30.0.toFloat(),paint)
    }
}