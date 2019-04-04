package com.selfview

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.emvl3kt.R

class GroupAdapter: BaseExpandableListAdapter {
    var context:Context?=null
    var groupnamelst:List<String>?=null
    var groupitemlst:List<List<String>>?=null
    constructor(ccontext:Context,groupnameLst :List<String>,groupitemLst:List<List<String>>)
    {
        context  = ccontext
        groupnamelst = groupnameLst
        groupitemlst =groupitemLst
    }
    override fun getGroup(groupPosition: Int): Any {
        return groupnamelst!![groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return  true
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        var groupHolder:GroupHolder
        if(convertView==null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.grouplayout,null)
            groupHolder = GroupHolder()
            groupHolder.tvName = view.findViewById(R.id.tv_group)
            //groupHolder.tvName!!.text = groupnamelst!![groupPosition]
            view!!.tag =groupHolder
        }else {
            groupHolder = convertView.tag as GroupHolder
        }
        if(isExpanded)
        {
            Log.d(javaClass.name,"expanded")
        }
        groupHolder!!.tvName!!.text = groupnamelst!![groupPosition]
        return view!!
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return groupitemlst!![groupPosition].size
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return  groupitemlst!![groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
       //To change body of created functions use File | Settings | File Templates.
        return  groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        //To change body of created functions use File | Settings | File Templates.
        var view = convertView
        var groupHolder:GroupItemHolder
        if(convertView==null)
        {
            view = LayoutInflater.from(context).inflate(R.layout.grouplistlayout,null)
            groupHolder = GroupItemHolder()
            groupHolder.tvName = view.findViewById(R.id.tv_group_item)
            view!!.tag = groupHolder
        }else {
            groupHolder = convertView.tag as GroupItemHolder
        }

        groupHolder!!.tvName!!.text = groupitemlst!![groupPosition][childPosition].toString()
        return view!!
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        //To change body of created functions use File | Settings | File Templates.
        return groupitemlst!!.size
    }
    class GroupHolder
    {
        var tvName :TextView?=null

    }
    class GroupItemHolder
    {
        var tvName :TextView? = null
    }
}