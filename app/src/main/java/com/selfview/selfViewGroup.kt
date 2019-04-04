package com.selfview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager


class selfViewGroup : ViewGroup {
    var tag:String = "selfViewGroup"
    var mScreenHeight:Int=0//屏幕高度

    constructor(context: Context):super(context){}
    constructor(context: Context,set: AttributeSet):super(context,set){
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        mScreenHeight = outMetrics.heightPixels
    }

    constructor(context: Context,set: AttributeSet,deflater:Int):super(context,set,0){}
    var mParentTop:Int =0
    var mParentLeft :Int = 0
    var mParentHeght:Int = 0
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        val mlp = layoutParams as ViewGroup.MarginLayoutParams
        mlp.height = mScreenHeight * childCount
        layoutParams = mlp
        Log.e(tag,"childCount"+childCount.toString())
        for (childerindex in 0..(childCount-1))
        {
            var view = getChildAt(childerindex)
            //布局 过滤不显示控件
            if(view.visibility != View.GONE)
            {
                //子控件布局
                //mParentLeft+=view.paddingLeft
                mParentTop+=view.paddingTop
                view.layout(mParentLeft,mParentTop,mParentLeft+view.measuredWidth,mParentTop+view.measuredHeight)
                mParentHeght+=view.measuredHeight
                mParentTop += view.measuredHeight
            }
        }
    }

    var childwidth:Int=0
    var childHeigth:Int = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mParentTop = paddingTop
        mParentLeft = paddingLeft
        //调置self width heigth
        var selfwidth = MeasureSpec.getSize(widthMeasureSpec)
        var selfwidthmode = MeasureSpec.getMode(widthMeasureSpec)
        var selfheigth = MeasureSpec.getSize(heightMeasureSpec)
        var selfheigthmode = MeasureSpec.getMode(heightMeasureSpec)
        //测量子元素
        measureChildren(widthMeasureSpec,heightMeasureSpec)
        //处理子元素宽度与高度
        if(childCount==0)
        {
            setMeasuredDimension(0,0)
        }
        else if(selfheigthmode == MeasureSpec.AT_MOST && selfwidthmode == MeasureSpec.AT_MOST)
        {

            for(childview in 0..childCount)
            {
                var view =  getChildAt(0)
                measureChild(view,widthMeasureSpec,heightMeasureSpec)
                //宽度
                var viewmargin = view.layoutParams as MarginLayoutParams
                childwidth+=(view.measuredWidth+view.paddingLeft+view.paddingTop+viewmargin.leftMargin+viewmargin.rightMargin)

                childHeigth +=(view.measuredHeight+view.paddingTop+view.paddingBottom+viewmargin.topMargin+viewmargin.bottomMargin)
            }
            Log.d(tag," two at_most "+ childHeigth)
            setMeasuredDimension(childwidth,childHeigth)
        }
        else if(selfheigth == MeasureSpec.AT_MOST)
        {
            //var childwidth:Int=0
            var childHeigth:Int = 0

            for(childview in 0..childCount)
            {
                var view =  getChildAt(0)
                measureChild(view,widthMeasureSpec,heightMeasureSpec)
                //宽度
                var viewmargin = view.layoutParams as MarginLayoutParams
               // childwidth+=(view.measuredWidth+view.paddingLeft+view.paddingTop+viewmargin.leftMargin+viewmargin.rightMargin)

                childHeigth +=(view.measuredHeight+view.paddingTop+view.paddingBottom+viewmargin.topMargin+viewmargin.bottomMargin)

            }
            Log.d(tag," height at_most "+ childHeigth)
            setMeasuredDimension(selfwidth,childHeigth)
        }else if (selfwidth == MeasureSpec.AT_MOST)
        {
            var childwidth:Int=0
            //var childHeigth:Int = 0

            for(childview in 0..childCount)
            {
                var view =  getChildAt(0)
                measureChild(view,widthMeasureSpec,heightMeasureSpec)
                //宽度
                var viewmargin = view.layoutParams as MarginLayoutParams
                 childwidth+=(view.measuredWidth+view.paddingLeft+view.paddingTop+viewmargin.leftMargin+viewmargin.rightMargin)

                //childHeigth +=(view.measuredHeight+view.paddingTop+view.paddingBottom+viewmargin.topMargin+viewmargin.bottomMargin)
            }
            Log.d(tag," width at_most "+ childHeigth)
            setMeasuredDimension(childwidth,selfheigth)
        }
    }


     var lastDownY: Int = 0
     var mScrollStart: Int = 0
     var mScrollEnd: Int = 0
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when(event!!.action)
        {
            MotionEvent.ACTION_DOWN->
            {
                //获取触摸点的位置
                lastDownY = event.y.toInt()
                startx = event.x
                mScrollStart = scrollY
                Log.d(tag,"onTouchEvent down ")
            }
            MotionEvent.ACTION_MOVE->
            {
                //移动处理
                var dy =  lastDownY - event.y
                scrollBy(0,dy.toInt())
                lastDownY = event.y.toInt()
            }
            MotionEvent.ACTION_UP->
            {
                mScrollEnd = scrollY
                //Y 轴偏移量
                var endy =  mScrollEnd - mScrollStart
                Log.d(tag,"Move y "+event.y.toString())
                Log.d(tag,"Move endy  "+endy)
                Log.d(tag,"Move event raw y   "+event.rawY)
                Log.d(tag,"Move top "+height)
                Log.d(tag,"Move scroll  "+scrollY)
                Log.d(tag,"Move mScreenHeight  "+mScreenHeight.toString())
                var firstview  =  getChildAt(0)
                //下滑 当超过滑动距离超过1／3屏幕时
                if(endy<0)
                {
                    if(-endy<mScreenHeight/3)
                    {
                        endy = -endy
                    }
                    else
                    {
                        endy = -mScreenHeight - endy
                    }
                }
                else
                {
                    if (endy < mScreenHeight / 3) {
                        endy= -endy
                    } else {
                        endy = mScreenHeight - endy
                    }
                }
                scrollBy( 0,endy.toInt())
            }
        }
        postInvalidate()
        return true
    }

    var startx:Float =0.0.toFloat()
    var rawx :Float = 0.0.toFloat()
    var rawy :Float = 0.0.toFloat()
    var pointId :Int  =0
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        var interceptflag = false
        var pointindex =  ev!!.findPointerIndex(pointId)
        when(ev!!.action)
        {
            MotionEvent.ACTION_DOWN->
            {
                //获取一个手指触控
                pointId = ev.getPointerId(0)
                interceptflag = false
                rawx = ev.x
                rawx = ev.y
                Log.d(tag,"intercept touch event Down "+ev.y)
                Log.d(tag,"intercept touch event Down "+ev.x)
            }
            MotionEvent.ACTION_MOVE->
            {
                Log.d(tag,"intercept touch event move "+ev.getX(pointindex))
                Log.d(tag,"intercept touch event move "+ev.getY(pointindex))
                Log.d(tag,"intercept touch event move "+Math.abs(ev.getY(pointindex)-lastDownY))
                Log.d(tag,"intercept touch event move "+Math.abs(ev.getX(pointindex)-startx))
                if(Math.abs(ev.x-startx)<Math.abs(ev.y-lastDownY))
                {
                    interceptflag = true
                }
                else
                {
                    interceptflag = false
                }
                Log.d(tag,"intercept touch event move " + interceptflag.toString())
            }
            MotionEvent.ACTION_UP->
            {
                interceptflag = false
                Log.d(tag,"intercept touch event up " + interceptflag.toString())
            }


        }
        rawx = ev.getX(pointindex)
        rawx = ev.getY(pointindex)
        Log.d(tag,"intercept flag " + interceptflag.toString())
        return interceptflag
    }

}