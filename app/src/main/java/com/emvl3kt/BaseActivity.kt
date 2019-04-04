package com.emvl3kt

import android.app.Activity
import android.os.Bundle
import android.view.View

open class BaseActivity : Activity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    override fun onClick(v: View?) {
        //To change body of created functions use File | Settings | File Templates.
    }
}
