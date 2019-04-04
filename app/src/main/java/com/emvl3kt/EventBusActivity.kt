package com.emvl3kt

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_event_bus.*
import org.bouncycastle.util.Integers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class EventBusActivity : BaseActivity() {

    companion object {
        var TAG = EventBusActivity().javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_bus)
        EventBus.getDefault().register(this)
        btnsendpost.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //To change body of created functions use File | Settings | File Templates.
                EventBus.getDefault().post(EventMsg())
            }
        })

        btnRxjava.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?){
                //To change body of created functions use File | Settings | File Templates.

                //注册->初始化->订阅
                Observable.create(ObservableOnSubscribe<String> {
                    it.onNext("firis")
                    it.onNext("second")
                    it.onNext("three")
                    it.onComplete()
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        subscribe(object : Observer<String> {
                    var disposable: Disposable? = null
                    override fun onComplete() {
                        Log.d(TAG, "Success")
                        //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun onError(e: Throwable) {
                        //To change body of created functions use File | Settings | File Templates.
                        Log.d(TAG, "faile")
                    }

                    override fun onNext(t: String) {
                        //To change body of created functions use File | Settings | File Templates.
                        Log.d(TAG, t.toString())
                    }

                    override fun onSubscribe(d: Disposable) {
                        //To change body of created functions use File | Settings | File Templates.
                        disposable = d
                    }
                })



            }
        })

        //map 进行数据转换
        RxJavaJustMap.setOnClickListener {
            Observable.just("1").map(Function<String,Int>{
                return@Function (it.toInt()+1)
            }).subscribe{
                Log.d(TAG,it.toString())
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun envent(eventmsg: EventMsg) {
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
        btnsendpost.text = "hello"
        btnsendpost.postInvalidate()
        Log.d("TAG", eventmsg.str!!.toString())
    }

    /* @Subscribe(threadMode = ThreadMode.MAIN)
     fun envent(eventmsg:EventMsg) {
         Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
         btnsendpost.text = "hello"
         btnsendpost.postInvalidate()
         Log.d("TAG",eventmsg.str!!.toString())
     }
 */
    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
