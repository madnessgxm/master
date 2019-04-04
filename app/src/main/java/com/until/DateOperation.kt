package com.until

import android.util.Log
import ui.wangpos.com.utiltool.DateUtil

class DateOperation:DateUtil {
    constructor():super() {}
    companion object {
        fun systemdatecompare():Boolean
        {
            val str = DateUtil.date2Str(DateUtil.str2Date(DateUtil.currentDatetime()), "yyyyMMddHHmmss")// 1971 < year < 2099
            if (Integer.parseInt(str.substring(0, 4)) < 1970) {
                Log.e("posdatetime", "system time error")
                return false
            }else
            {
                return true
            }
        }
    }
}