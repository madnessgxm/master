package com.mvvmfactory

import android.animation.ObjectAnimator
import android.app.ActivityManager
import android.databinding.*

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.emvl3kt.BaseActivity
import com.emvl3kt.R
import com.emvl3kt.databinding.ActivityDataBindingBinding
import com.httpProxy.HttpCallBack
import com.httpProxy.HttpHelper
import com.httpProxy.weatherinfo


class DataBindingActivity : BaseActivity() {

    var userModel=UserModel()
    var userModeltest =ObserableFieldUserName()
    var binding:ActivityDataBindingBinding?=null
    var maptest:ObservableMap<String,String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding =  DataBindingUtil.setContentView<ActivityDataBindingBinding>(this@DataBindingActivity,R.layout.activity_data_binding)
        userModel.username="hello word"
        userModel.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback()
        {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                //To change body of created functions use File | Settings | File Templates.
                if(propertyId == com.emvl3kt.BR.username)
                {
                    Log.d("hello word ","test")
                }
            }
        })

        binding.user = userModel
        binding.ivew = this
        userModeltest.username = ObservableField<String>("Databinding")
        binding.obserableFiledUpserModel =userModeltest

        var obsealbelist = ObservableArrayList<String>()
        obsealbelist.add("nihao")
        obsealbelist.add("1")
        obsealbelist.add("2")
        binding.olist = obsealbelist
        binding.str = 1
        maptest =ObservableArrayMap<String,String>()
        maptest!!.put("mapkey","测试")
        binding.maptest = maptest
        binding.mapkey = "mapkey"
    }


    fun click(iview:View){
        if(iview.id == R.id.btnOnClick) {
            Toast.makeText(this@DataBindingActivity, "", Toast.LENGTH_SHORT).show()
            userModel.username = "你好"
            userModeltest.username.set("Databinding Test")
            maptest!!.put("mapkey", "hello ")
        }
        else if(iview.id == R.id.btnSend)
        {

            HttpHelper.obtain().get(HttpHelper.url,java.util.HashMap<String,Object>(),object :HttpCallBack<weatherinfo>(){
                override fun onSuccess(ret: weatherinfo) {
                     //To change body of created functions use File | Settings | File Templates.
                    userModel.username = ret.toString();
                }

                override fun fail(rest: String) {
                    Toast.makeText(this@DataBindingActivity, "fail", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}
