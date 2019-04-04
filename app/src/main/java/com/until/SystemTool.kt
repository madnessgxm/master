package com.until

import android.os.Environment

class SystemTool
{
    constructor()
    companion object {

        var jsonParameterPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/wz_parameter/"
    }
}
