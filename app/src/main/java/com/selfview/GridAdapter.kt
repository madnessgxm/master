package com.selfview

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.emvl3kt.R
import com.emvl3kt.ViewSwitcherActivity

class GridAdapter :BaseAdapter {

    var context:Context?=null
    var lstGridItem: java.util.ArrayList<CGridItem>?=null
    constructor(ccontext:Context, lstgriditem: ArrayList<CGridItem>){
        context = ccontext
        lstGridItem =lstgriditem
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
         //To change body of created functions use File | Settings | File Templates.
        var view = convertView
        var gridItem:GridItem
        if(view==null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.gridlayoutitem,null)
            gridItem = GridItem()
            gridItem.img =view!!.findViewById(R.id.img)
            gridItem.imgtxt = view!!.findViewById(R.id.imgtxt)
            view!!.tag = gridItem
        }
        else
        {
            gridItem = view!!.tag as GridItem
        }
        gridItem.img!!.setImageResource( (getItem(position) as CGridItem).imgid)
        gridItem.imgtxt!!.text= (getItem(position) as CGridItem).imgname

        return view
    }

    override fun getItem(position: Int): Any {
        //To change body of created functions use File | Settings | File Templates.
        return lstGridItem!!.get(ViewSwitcherActivity.screenNo * ViewSwitcherActivity.NUMBER_PER_SCREEN + position)
    }

    override fun getItemId(position: Int): Long {
        //To change body of created functions use File | Settings | File Templates.
        return position.toLong()
    }

    override fun getCount(): Int {
         //To change body of created functions use File | Settings | File Templates.
        if (ViewSwitcherActivity.screenNo == ViewSwitcherActivity.screenCount - 1
                && lstGridItem!!.size % ViewSwitcherActivity.NUMBER_PER_SCREEN != 0) {
            // 最后一屏显示的程序数为应用程序的数量对NUMBER_PER_SCREEN求余
            return lstGridItem!!.size % ViewSwitcherActivity.NUMBER_PER_SCREEN
        }
        // 否则每屏显示的程序数量为NUMBER_PER_SCREEN
        return ViewSwitcherActivity.NUMBER_PER_SCREEN

    }

    class GridItem
    {
        var img:ImageView?=null
        var imgtxt:TextView?=null
    }
}