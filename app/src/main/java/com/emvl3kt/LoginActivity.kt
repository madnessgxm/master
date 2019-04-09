package com.emvl3kt

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_POINTER_DOWN
import android.view.View
import android.widget.Toast
import com.model.ICallBack
import com.presenter.PersenterControl
import com.presenter.PersenterFactory
import com.view.DlgToast
import kotlinx.android.synthetic.main.activity_login.*
import myself.annotationprocessor.testannotation
import java.util.*

@testannotation(name="")
class LoginActivity : BaseActivity() {

    val handler =object :Handler()
    {
        override fun handleMessage(msg: Message?) {
            when(msg?.what)
            {
                1->{
                    var intent = Intent()
                    intent.setClass(this@LoginActivity,ManagerActivity().javaClass)
                    startActivity(intent)
                }
                2->{
                    var intent = Intent()
                    intent.setClass(this@LoginActivity, MainActivity().javaClass)
                    startActivity(intent)
                }
                else->
                {
                    DlgToast.ShowOkCancelTips(this@LoginActivity,"Tips",msg?.obj.toString(), onOkClickListener = object:View.OnClickListener{
                        override fun onClick(v: View?) {
                            Toast.makeText(this@LoginActivity,"",Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener(this)

    }


   /* override fun onTouchEvent(event: MotionEvent?): Boolean {
      if(event!!.action == MotionEvent.ACTION_DOWN)
       (imgidtips as View).scrollTo(event.getX().toInt() -(imgidtips as View).scrollX ,0)

       return super.onTouchEvent(event)
    }*/

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.btnLogin -> {
                val username = txtusername?.text.toString()
                val pwd = txtpwd?.text.toString()
                if(username.equals("99") && pwd.equals("20080909"))
                {
                    handler.sendEmptyMessage(1)
                }
                else if(username.equals("01") && pwd.equals("0000")) {
                    PersenterFactory.Login(txtusername?.text.toString(), txtpwd?.text.toString(), iCallBack = object : ICallBack {
                        override fun error(errorcore: Int, objects: Objects) {
                            var msg = Message()
                            msg.what = 3
                            msg.obj = objects.toString()
                            handler.sendMessage(msg)
                        }
                        override fun success() {
                           handler.sendEmptyMessage(2)
                        }
                    })
                }
                else
                {
                    var msg = Message()
                    msg.what = 3
                    msg.obj = "username or password error"
                    handler.sendMessage(msg)
                }
            }
        }
    }
}
