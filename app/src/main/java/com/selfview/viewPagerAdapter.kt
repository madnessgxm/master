package com.selfview

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

class viewPagerAdapter:PagerAdapter {

    var pagerlist:ArrayList<View>?=null
    var pagertitle:ArrayList<String>?=null
    constructor(plst:ArrayList<View>,ptitle:ArrayList<String>)
    {
        pagerlist = plst
        pagertitle = ptitle
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return pagerlist!!.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(pagerlist!![position])
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = pagerlist!![position]
        container.addView(view)
        return view
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pagertitle!![position]
    }
}