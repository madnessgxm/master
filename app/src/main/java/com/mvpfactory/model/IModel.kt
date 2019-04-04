package com.mvpfactory.model

interface IModel {
    interface loginLisenter{
        fun onsuccess()
        fun onerror(errorCode:Int)
    }
    fun login(username:String,pwd:String,lgLisenter:loginLisenter)
}