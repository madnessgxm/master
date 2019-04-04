package com.httpProxy

interface IHttpProcessor {
    fun get(url:String,map:HashMap<String,Object>,callBack: ICallBack)
    fun post(url:String,map:HashMap<String,Object>,callBack: ICallBack)
}