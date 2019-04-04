package com.httpProxy

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType

abstract class HttpCallBack<Rescult>:ICallBack {

    constructor()

    override fun fail(rest: String) {
       fail(rest)
    }

    abstract fun onSuccess(ret:Rescult)

    override fun success(rest: String) {
         //To change body of created functions use File | Settings | File Templates.
        var gson = Gson()
        var t =  this.javaClass.genericSuperclass // 获取支持的所有类型
        var clz =  (t as ParameterizedType).actualTypeArguments[0]// 类型转换
        var ret =  gson.fromJson(rest,clz) as Rescult //输入信息
        onSuccess(ret)
    }
}