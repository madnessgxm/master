package com.emvl3kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.selfview.viewPagerAdapter
import kotlinx.android.synthetic.main.activity_view_paper.*

class ViewPaperActivity : BaseActivity() {

    var pagerLst :ArrayList<View>?=null
    var viewpageadapter:viewPagerAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_paper)
        pagerLst = ArrayList()
        var viewfirst =   LayoutInflater.from(this@ViewPaperActivity).inflate(R.layout.viewpager_layout_first,null,false)
        pagerLst!!.add(viewfirst)
        var viewsecord=  LayoutInflater.from(this@ViewPaperActivity).inflate(R.layout.viewpager_layout_second,null,false)
        pagerLst!!.add(viewsecord)
        var viewthrid =   LayoutInflater.from(this@ViewPaperActivity).inflate(R.layout.viewpager_layout_thrid,null,false)
        pagerLst!!.add(viewthrid)
        viewpageadapter = viewPagerAdapter(pagerLst!!, arrayListOf("中国","日本","美国"))
        viewpagertest.adapter = viewpageadapter

    }
}
