package com.selfview

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import com.emvl3kt.R

class lruCacheImage :BaseAdapter, AbsListView.OnScrollListener{

     var url:Array<String>?=null
     var context: Context?=null
     var listview:ListView?=null
     var imgLoader :imgLoader?=null
     var mStart: Int = 0// 第一张可见图片的下标
     var mEnd: Int = 0// 最后一张可见图片的下标
     var mFirstIn:Boolean=true

    constructor(tmpcontext: Context,imgurl:Array<String>,lstView:ListView){
        context = tmpcontext
        url = imgurl
        listview =lstView
        imgLoader = imgLoader(listview)
    }

    override fun getItem(position: Int): Any {
        return url!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return url!!.size
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var imgholder:imgHolder
        if(view==null)
        {
            imgholder = imgHolder()
            view = LayoutInflater.from(context).inflate(R.layout.lrucacheimglist,null)
            view.tag = imgholder
        }else
        {
            imgholder  = view.tag as imgHolder
        }

        imgholder.img = view!!.findViewById(R.id.img)
        imgholder.img.setImageResource(R.drawable.pwd)
        imgholder.img.tag =url!![position]
        imgLoader!!.show(imgholder.img, position.toString())
        return view
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        //第一次显示时候调用
        if (mFirstIn&&visibleItemCount > 0) {
            imgLoader!!.show(url,mStart, mEnd)
            mFirstIn = false
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        if (scrollState == SCROLL_STATE_IDLE) {
            //加载可见项
            imgLoader!!.show(url,mStart, mEnd)
        } else {
            // 停止任务
            imgLoader!!.cancelAllTask()
        }
    }
    class imgHolder
    {
       lateinit var img:ImageView
    }
}