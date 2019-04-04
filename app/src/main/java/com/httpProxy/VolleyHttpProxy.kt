package com.httpProxy

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class VolleyHttpProxy:IHttpProcessor {


    var context:Context?=null
    constructor(ctext:Context)
    {
        context = ctext
    }

    override fun get(url: String, map: HashMap<String, Object>, callBack: ICallBack) {
        //To change body of created functions use File | Settings | File Templates.
       var requestQueue =  Volley.newRequestQueue(context)
        var request=StringRequest(Request.Method.GET,url,object : Response.Listener<String>{
            override fun onResponse(response: String?) {
                //To change body of created functions use File | Settings | File Templates.
                callBack.success(response!!)
            }
        },object :Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                //To change body of created functions use File | Settings | File Templates.
                callBack.fail(error.toString())
            }
        })
        requestQueue.add(request)
    }

    override fun post(url: String, map: HashMap<String, Object>, callBack: ICallBack) {
        //To change body of created functions use File | Settings | File Templates.
        var requestQueue =  Volley.newRequestQueue(context)
        var request=StringRequest(Request.Method.POST,url,object : Response.Listener<String>{
            override fun onResponse(response: String?) {
                //To change body of created functions use File | Settings | File Templates.
                callBack.success(response!!)
            }
        },object :Response.ErrorListener{
            override fun onErrorResponse(error: VolleyError?) {
                //To change body of created functions use File | Settings | File Templates.
                callBack.fail(error.toString())
            }
        })
        requestQueue.add(request)

    }
}