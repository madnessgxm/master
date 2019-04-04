package com.model

import java.util.*

public interface ICallBack {
    fun success()
    fun error(errorcore:Int,objects: Objects)
}
