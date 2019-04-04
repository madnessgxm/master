package com.selfview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View

//自定义分割线 RecyclerView
class RecyclerViewItemDivider : RecyclerView.ItemDecoration {
    var drawable:Drawable?=null
    var context:Context?=null
    constructor(ccontext:Context,cdrawable:Int)
    {
        context = ccontext
        drawable = context!!.resources.getDrawable(cdrawable)
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        var left = parent!!.paddingLeft
        var right = parent!!.width - parent.paddingLeft
        var childCount = parent.childCount
        for (i in 0..(childCount-1)) {
            var child = parent!!.getChildAt(i)
            var  params = child.layoutParams as RecyclerView.LayoutParams
            var top = child!!.bottom + params.bottomMargin
            var bottom = top + drawable!!.intrinsicHeight
            drawable!!.setBounds(left, top, right, bottom);
            drawable!!.draw(c)
        }
    }
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        //super.getItemOffsets(outRect, view, parent, state)
        outRect!!.set(0, 0, 0, drawable!!.intrinsicWidth)
    }
}