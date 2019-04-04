package com.emvl3kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_swipe_refresh_layout.*

class SwipeRefreshLayoutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_refresh_layout)
        swipeRefreshLayout.setOnRefreshListener(object:SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                 //To change body of created functions use File | Settings | File Templates.
                swipeRefreshLayout.isRefreshing=false
            }
        })
    }
}
