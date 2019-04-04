package com.selfview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class MyFilpperAdpater : BaseAdapter {

    var context:Context?=null
    var imgidarr:IntArray?=null
    constructor(tmpcontext: Context,imgid:IntArray)
    {
        context = tmpcontext
        imgidarr = imgid
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //To change body of created functions use File | Settings | File Templates.
        var view = convertView
        var img :ImageView
        if(view == null)
        {
            img = ImageView(context)
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            // 为imageView设置布局参数
            img.setLayoutParams( ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view = img
        }else
        {
          img =   view.tag  as ImageView
        }
        img.setImageResource(imgidarr!![position])
        return img
    }

    override fun getItem(position: Int): Any {
        //To change body of created functions use File | Settings | File Templates.
        return imgidarr!![position]
    }

    override fun getItemId(position: Int): Long {
        //To change body of created functions use File | Settings | File Templates.
      return  position.toLong()
    }

    override fun getCount(): Int {
        //To change body of created functions use File | Settings | File Templates.
       return imgidarr!!.size
    }
}