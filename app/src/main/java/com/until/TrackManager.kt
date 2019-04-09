package com.until

import myself.annotationprocessor.IData
import java.util.HashMap

class TrackManager {
    var hashMap:HashMap<String,String>?=null
    constructor(){}
    companion object {
      private var trackManager = TrackManager()
        fun getInstence():TrackManager
        {
            synchronized(TrackManager.javaClass)
            {
                if(trackManager==null) {
                    trackManager = TrackManager()
                }
                return trackManager
            }
        }
    }


    fun gointent(strkey:String):String
    {
        return hashMap!![strkey].toString()
    }

    fun loadinfo()
    {
        try {
             var classname:String = "com.xud.TrackManager\$Helper"
             var classc = Class.forName(classname)
             var idata = classc.newInstance() as IData
             hashMap = HashMap<String,String>()
             idata.loadInto(hashMap)
        }catch (ex:Exception)
        {
            ex.printStackTrace()
        }

    }
}