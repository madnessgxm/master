package com.httpProxy

import java.util.*

class HttpHelper : IHttpProcessor {
    companion object {
        private var httpHelper: HttpHelper? = null
        private var httpProcessor: IHttpProcessor? = null
        fun init(httpPro: IHttpProcessor) {
            httpProcessor = httpPro
        }

        @Synchronized
        fun obtain(): HttpHelper {

            if (httpHelper == null) {
                httpHelper = HttpHelper()
            }
            return httpHelper!!
        }

        var url:String = "http://www.weather.com.cn/data/sk/101010100.html"

    }

    override fun get(url: String, map: HashMap<String, Object>, callBack: ICallBack) {
        //To change body of created functions use File | Settings | File Templates.
        httpProcessor!!.get(getURLCovert(url,map), map, callBack)
    }

    override fun post(url: String, map: HashMap<String, Object>, callBack: ICallBack) {
        //To change body of created functions use File | Settings | File Templates.
        httpProcessor!!.post(getURLCovert(url,map), map, callBack)
    }

    //将map与URL拼接
    private fun getURLCovert(url: String, map: HashMap<String, Object>): String {
        if(map.size<=0)
        {
            return url
        }
        var paramstr: String = ""
        map.forEach { (key, value) ->
            paramstr = paramstr + key + "=" + value.toString() + "&"
        }

        return url + "?" + paramstr.substring(0,paramstr.length -1)
    }
}