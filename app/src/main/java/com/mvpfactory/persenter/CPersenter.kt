package com.mvpfactory.persenter

import com.mvpfactory.model.IModel
import com.mvpfactory.model.LoginModel
import com.mvpfactory.view.IView
import java.lang.ref.WeakReference

class CPersenter:IPersenter,IModel.loginLisenter{

    var ivew:WeakReference<IView>?=null
    var imode:IModel=LoginModel()
    constructor(iView: IView)
    {
        ivew = WeakReference<IView>(iView)
    }

    private fun isBinding():Boolean
    {
        return ivew != null
    }
    override fun Login(username: String, pwd: String) {
        //To change body of created functions use File | Settings | File Templates.
        try {
            if(isBinding())
            {
                ivew!!.get()!!.showProcessing()
                imode.login(username,pwd,this)
            }
            else
            {
                ivew!!.get()!!.hideProcessed()
            }
        }
        catch (ex:Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun onerror(errorCode: Int) {
        //To change body of created functions use File | Settings | File Templates.
        if(!isBinding())
            ivew!!.get()!!.onFail()
    }

    override fun onsuccess() {
        //To change body of created functions use File | Settings | File Templates.
        if(!isBinding())
            ivew!!.get()!!.onSuccess()
    }
}