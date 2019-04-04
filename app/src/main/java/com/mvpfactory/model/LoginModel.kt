package com.mvpfactory.model

import android.os.Handler

class LoginModel :IModel {
    override fun login(username: String, pwd: String, lgLisenter: IModel.loginLisenter) {
        //To change body of created functions use File | Settings | File Templates.
        Handler().postDelayed(Runnable {
            try {

                lgLisenter.onsuccess()
            }catch (ex:Exception)
            {
                ex.printStackTrace()
                lgLisenter.onerror(-1)
            }
        },5000)
    }
}
