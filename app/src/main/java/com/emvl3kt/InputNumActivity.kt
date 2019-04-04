package com.emvl3kt

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class InputNumActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_num)
        CtitleBar.backOnClick(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.img_back -> {
                this.finish()
            }
        }
    }
}
