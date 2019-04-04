package com.emvl3kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.selfview.selfTextView
import com.view.firstLinelayout
import com.view.secordLayout
import kotlinx.android.synthetic.main.activity_scoll.*

class ScollActivity : BaseActivity() {

    var strArray = "London Bangkok Paris Dubai Istanbul NewYork Singapore Kuala Lumpur HongKong Tokyo Barcelona Vienna Los Angeles Prague Rome Seoul Mumbai Jakarta Berlin Beijing Moscow"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoll)
        selftextview.setwidthheight(window.windowManager.defaultDisplay.width.toInt(),100);
        selftextview.invalidate()
        for (str in strArray) {
            var txtview = TextView(this)
            txtview.text=str.toString()
            linehear.addView(txtview)
        }
    }
}

