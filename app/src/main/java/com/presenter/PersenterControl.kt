package com.presenter

import com.model.ICallBack
import org.json.JSONObject

class PersenterControl:IPersenter
{
    override fun addAid(string: String):Boolean {
        val jsonObject = JSONObject()
        val rootJson = jsonObject.getJSONObject(string)
        val jsonArray= rootJson.getJSONArray("aid")

        return true
    }

    override fun login(userid: String, pwd: String, iCallBack: ICallBack) {

        iCallBack?.success()
    }
}
