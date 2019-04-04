package com.context

import android.app.Application
import android.content.Context
import android.util.Log
import com.facebook.stetho.Stetho
import com.httpProxy.HttpHelper
import com.httpProxy.OkHttpProxy
import com.httpProxy.VolleyHttpProxy
import com.until.DateOperation
import sdk4.wangpos.libemvbinder.EmvCore
import ui.wangpos.com.utiltool.DateUtil
import wangpos.sdk4.libbasebinder.BankCard
import wangpos.sdk4.libbasebinder.Core
import wangpos.sdk4.libbasebinder.Printer
import wangpos.sdk4.libkeymanagerbinder.Key
import kotlin.concurrent.thread

class AppContext : Application {
    var context: Context? = null

    constructor(){
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        sdkinit()
    }

    fun sdkinit() {
        try {
            Stetho.initializeWithDefaults(this)
        }catch (ex:Exception)
        {
            ex.printStackTrace()
        }
        HttpHelper.init(OkHttpProxy())
        thread(start = true)
        {
            //SDK init
            /*emvCore = EmvCore(context)
            mCore = Core(context)
            mKey = Key(context)
            bankCard = BankCard(context)
            mPrinter = Printer(context)
            //setting system time
            if(DateOperation.systemdatecompare())
            {
                var datestr = DateUtil.getCurDateStr("yyyyMMddHHmmss")
                mCore!!.setDateTime(datestr.toByteArray(Charsets.UTF_8))
            }*/
        }
    }

    companion object {

        var emvCore: EmvCore? = null
        var mCore: Core? = null
        var mKey: Key? = null
        var bankCard: BankCard? = null
        var mPrinter: Printer? = null
    }
}