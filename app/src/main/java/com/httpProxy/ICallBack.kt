package com.httpProxy

interface ICallBack {
    fun success(rest:String)
    fun fail(rest: String)
}