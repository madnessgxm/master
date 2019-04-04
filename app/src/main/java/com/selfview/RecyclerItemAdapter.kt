package com.selfview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.emvl3kt.R


class RecyclerItemAdapter :RecyclerView.Adapter<RecyclerView.ViewHolder> {

    val HearTitle:Int=0
    val FlootTitle:Int = 1
    val ContextTitle = 2

    lateinit var context:Context
    var mInflater: LayoutInflater? = null
    lateinit var mDatas:ArrayList<String>
    var onItemClickListener:OnItemClickListener?=null
    set(value) {
        field = value
    }
    var onLongItemClickListener:OnLongItemClickListener?=null
    set(value) {
        field = value
    }

    override fun getItemViewType(position: Int): Int {
        if(position ==0)
        {
            return HearTitle
        }else if (position==mDatas.size+1)
        {
            return FlootTitle
        }else
        {
            return ContextTitle
        }
    }

    constructor(ccontext:Context,mdata:ArrayList<String>)
    {
        context = ccontext
        mInflater = LayoutInflater.from(context)
        mDatas = mdata
    }

    fun addItem(str:String,postion: Int)
    {
        mDatas.add(postion,str)
        notifyItemInserted(postion)
        if(postion!=mDatas.size)
        {
            notifyItemRangeChanged(postion,mDatas.size)
        }
    }

    fun removeItem(postion: Int)
    {
        mDatas.removeAt(postion)
        notifyItemRemoved(postion)
        if(postion!=mDatas.size)
        {
            notifyItemRangeChanged(postion,mDatas.size)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        //To change body of created functions use File | Settings | File Templates.
        var view :View?=null
        if(p1 == HearTitle)
        {
            view =  mInflater!!.inflate(R.layout.recyclerhearlayout,p0,false)
            var headerViewHolder = HeaderViewHolder(view)
            return  headerViewHolder
        }else if(p1 == FlootTitle)
        {
            view =  mInflater!!.inflate(R.layout.recyclerfoorlayout,p0,false)
            var footerViewHolder = FooterViewHolder(view)
            return  footerViewHolder
        }else {
            view = mInflater!!.inflate(R.layout.recyclerview_item_layout, p0, false)
            var cViewHoler = CViewHoler(view)
            return cViewHoler
        }

    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
         //To change body of created functions use File | Settings | File Templates. mDatas[p1]
        if(p0 is HeaderViewHolder)
        {

        }else if (p0 is FooterViewHolder)
        {

        }else {
            var str: String = mDatas[p1-1]

            var cview = p0 as CViewHoler
            cview!!.tvView!!.text = str
            if (onItemClickListener != null) {
                cview!!.tvView!!.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        //To change body of created functions use File | Settings | File Templates.
                        onItemClickListener!!.onClick(v!!, p1)
                    }
                })
                cview!!.imgView!!.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        //To change body of created functions use File | Settings | File Templates.
                        onItemClickListener!!.onClick(v!!, p1)
                    }
                })
            }
            if (onLongItemClickListener != null) {
                cview.tvView!!.setOnLongClickListener(object : View.OnLongClickListener {
                    override fun onLongClick(v: View?): Boolean {
                        return onLongItemClickListener!!.onLongClick(v!!, p1)

                    }
                })
                cview.imgView!!.setOnLongClickListener(object : View.OnLongClickListener {
                    override fun onLongClick(v: View?): Boolean {
                        return onLongItemClickListener!!.onLongClick(v!!, p1)
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int {
        //To change body of created functions use File | Settings | File Templates.
        return if(mDatas==null) 0 else{ mDatas.size+2}
    }


    interface OnItemClickListener{
        fun onClick(parent:View,postion:Int):Boolean
    }

    interface OnLongItemClickListener{
        fun onLongClick(parent: View,postion: Int):Boolean
    }

    class CViewHoler:RecyclerView.ViewHolder
    {
        constructor(view:View):super(view)
        {
            imgView = view.findViewById(R.id.imgView)
            tvView = view.findViewById(R.id.tvView)
        }
        var imgView: ImageView?=null
        var tvView:TextView?=null
    }

    //头部 ViewHolder
     class HeaderViewHolder : RecyclerView.ViewHolder {

        constructor(item:View) : super(item) {}
    }
    //底部 ViewHolder
     class FooterViewHolder : RecyclerView.ViewHolder
    {
        constructor(item:View) : super(item) {}
    }

}