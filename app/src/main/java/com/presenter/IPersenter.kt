package com.presenter

import com.model.ICallBack

interface IPersenter {
    fun login(userid:String,pwd:String,iCallBack: ICallBack)
    fun addAid(string: String):Boolean
}
