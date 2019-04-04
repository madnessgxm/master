package com.emvl3kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.StackView
import com.selfview.StackViewAdapter
import kotlinx.android.synthetic.main.activity_stack.*
import java.util.*
import kotlin.math.log

class StackActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stack)
        var imgid = intArrayOf(R.drawable.back,R.drawable.balance,R.drawable.purchase,R.drawable.pwd)
        var stackViewAdapter =StackViewAdapter(this@StackActivity,imgid)
        stackviewtest.adapter = stackViewAdapter

        prev_btn.setOnClickListener {
            Log.d("getview","")
            stackviewtest.showPrevious()
        }
        next_btn.setOnClickListener({
            stackviewtest.showNext()
        })
    }
}
