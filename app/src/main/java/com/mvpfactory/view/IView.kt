package com.mvpfactory.view

interface IView {
    fun showProcessing()
    fun hideProcessed()
    fun onSuccess()
    fun onFail()
}