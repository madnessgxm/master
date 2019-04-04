package com.mvpfactory.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emvl3kt.BaseActivity
import com.emvl3kt.R
import com.mvpfactory.persenter.CPersenter
import com.mvpfactory.persenter.IPersenter
import kotlinx.android.synthetic.main.activity_login2.*

class LoginActivity : BaseActivity(),IView {

    var cPersenter :IPersenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        btnLogin.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                //To change body of created functions use File | Settings | File Templates.
                cPersenter = CPersenter(this@LoginActivity)
                cPersenter!!.Login("01","0000")
            }
        })

    }

    override fun showProcessing() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProcessed() {
         //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFail() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccess() {
       //To change body of created functions use File | Settings | File Templates.
    }

}
