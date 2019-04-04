package com.emvl3kt

import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.view.CustomTitleBar
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CtitleBar.backOnClick(this)
        imgpurchase.setOnClickListener(this)
        imgbance.setOnClickListener(this)
        imgrefund.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.img_back->
            {
                this.finish()
            }
            R.id.imgpurchase->
            {
                var intent = Intent(this,InputNumActivity().javaClass)
                startActivity(intent)
            }
            R.id.imgbance->
            {

            }
            R.id.imgrefund->
            {

            }
        }
    }
}
