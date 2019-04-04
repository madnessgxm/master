package com.emvl3kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.selfview.RecyclerItemAdapter
import com.selfview.RecyclerViewItemDivider
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : BaseActivity(),RecyclerItemAdapter.OnLongItemClickListener,
        RecyclerItemAdapter.OnItemClickListener{
    var adapter:RecyclerItemAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        var linearLayoutManager  = LinearLayoutManager(this@RecyclerViewActivity)
        recyclerView.layoutManager  = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(RecyclerViewItemDivider(this@RecyclerViewActivity,R.drawable.splitline))
        adapter =RecyclerItemAdapter(this@RecyclerViewActivity, arrayListOf("中国","美国","日本","泰国","中国","美国","日本","泰国","中国","美国","日本","泰国","中国","美国","日本","泰国"))
        recyclerView.adapter =adapter
        adapter!!.onItemClickListener =this
        adapter!!.onLongItemClickListener = this
    }

    override fun onClick(parent: View, postion: Int):Boolean {
       //To change body of created functions use File | Settings | File Templates.
        adapter!!.addItem("中华人民",postion+1)
        Toast.makeText(this@RecyclerViewActivity,"onClick Postion "+postion.toString(),Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onLongClick(parent: View, postion: Int):Boolean {
        adapter!!.removeItem(postion)
        //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this@RecyclerViewActivity,"onLongClick Postion "+postion.toString(),Toast.LENGTH_SHORT).show()
        return true
    }

}
