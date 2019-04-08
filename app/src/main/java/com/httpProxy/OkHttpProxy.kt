package com.httpProxy

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.*
import java.io.IOException

class OkHttpProxy :IHttpProcessor {

    constructor()

    override fun get(url: String, map: HashMap<String, Object>, callBack: ICallBack) {
        //To change body of created functions use File | Settings | File Templates.
        var ok =OkHttpClient()
        ok.networkInterceptors().add(StethoInterceptor())
        var okhttpClict = OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build()

        var req = Request.Builder().url(url).get().build()
        var call = okhttpClict.newCall(req)
        call.enqueue(object :Callback{
            override fun onResponse(call: Call?, response: Response?) {
                //To change body of created functions use File | Settings | File Templates.
                if(response!!.isSuccessful)
                {
                    callBack.success(response.body()!!.string())
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
               //To change body of created functions use File | Settings | File Templates.
                callBack.fail(e!!.message.toString())
            }
        })

    }

    override fun post(url: String, map: HashMap<String, Object>, callBack: ICallBack) {
        //To change body of created functions use File | Settings | File Templates.
        var okhttpClict = OkHttpClient()
        var req = Request.Builder().url(url).addHeader("User-Agent","")
                .post(RequestBody.create(MediaType.parse(""),"")).build()
        var call = okhttpClict.newCall(req)
        call.enqueue(object :Callback{
            override fun onResponse(call: Call?, response: Response?) {
                //To change body of created functions use File | Settings | File Templates.
                if(response!!.isSuccessful)
                {
                    callBack.success(response.body()!!.string())
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                //To change body of created functions use File | Settings | File Templates.
                callBack.fail(e!!.message.toString())
            }
        })
    }
}