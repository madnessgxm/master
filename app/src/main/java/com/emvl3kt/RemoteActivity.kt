package com.emvl3kt

import android.animation.ObjectAnimator
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.appwidget.AppWidgetHostView
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.RemoteViews
import android.support.v4.widget.ExploreByTouchHelper.HOST_ID
import android.appwidget.AppWidgetHost
import android.content.ComponentName
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.TYPE_PHONE
import android.widget.Button
import kotlinx.android.synthetic.main.activity_remote.*
import kotlin.math.log


class RemoteActivity : BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote)
        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        btnwidget.setOnClickListener(View.OnClickListener {
            /*var intent = Intent()
            var componentName = ComponentName(this@RemoteActivity,NewAppWidget().javaClass)
            var ints = AppWidgetManager.getInstance(this@RemoteActivity).getAppWidgetIds(componentName)
            var bundle = Bundle()
            bundle.putIntArray("com.action.click",ints)
            intent.putExtras(bundle)
            intent.action="com.action.click"
            sendBroadcast(intent)*/

             //var objectAnimator= ObjectAnimator.ofFloat(btnwidget,"translationY",-btnwidget.height.toFloat())
            //alpha,rotaion,translate,scale 四种属性

            var objectAnimator=  ObjectAnimator.ofFloat(btnwidget,"rotation",0.0.toFloat(),180.0.toFloat(),0.0.toFloat())
            objectAnimator.setDuration(2000.0.toLong())
            objectAnimator.start()

           /* var objectAnimator1 = ObjectAnimator.ofInt(btnwidget,"width",0x00)
            objectAnimator1.setDuration(2000.0.toLong())
            objectAnimator1.start()*/

        })


        //window manage
        var btntxt = Button(this@RemoteActivity)
        btntxt.text = "windows test"
        var layoutParams = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,3,0,PixelFormat.TRANSPARENT)
        layoutParams.flags =WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
        layoutParams.gravity = Gravity.LEFT
        layoutParams.x = 100
        layoutParams.y = 300
        btntxt.layoutParams = layoutParams
        val wmManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

        wmManager.addView(btntxt,layoutParams)
        //simpleNotify(notificationManager)
        /*layoutNotify(notificationManager)
        //
        Thread(Runnable {
            //update Notify
            Log.d("tag","ThreadRunable")
            Looper.prepare()
            simpleNotify(2,notificationManager)
            Looper.loop()

        }).start()*/

       /* var componentName = ComponentName(this@RemoteActivity,NewAppWidget().javaClass)
        var ints =  AppWidgetManager.getInstance(this).getAppWidgetIds(componentName)
        Log.d("tag","activity number "+ints.size.toString())
        var remoteViews = RemoteViews(this@RemoteActivity.packageName,R.layout.new_app_widget)
        remoteViews.setTextViewText(R.id.appwidget_text,"why")
        AppWidgetManager.getInstance(this).updateAppWidget(componentName,remoteViews)
       */

    }

    override fun onStop() {
        Log.d("tag","onStop")

        super.onStop()
    }
    fun simpleNotify(index:Int=1,manger: NotificationManager){
        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        var builder = NotificationCompat.Builder(this@RemoteActivity,"")
        //Ticker是状态栏显示的提示
        builder.setTicker("简单Notification")
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle("标题")
        //第二行内容 通常是通知正文
        builder.setContentText("通知内容")
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        builder.setSubText("这里显示的是通知第三行内容！")
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        builder.setContentInfo("2");
        //number设计用来显示同种通知的数量和ContentInfo的位置一样，如果设置了ContentInfo则number会被隐藏
        builder.setNumber(2)
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true)
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher)
        //下拉显示的大图标
        //builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.balance));
        var intent = Intent(this@RemoteActivity,LoginActivity().javaClass)
        var pIntent = PendingIntent.getActivity(this,1,intent,0)
        //点击跳转的intent
        builder.setContentIntent(pIntent);
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        var notification = builder.build()
        manger.notify(index,notification);
    }
    lateinit var builder:NotificationCompat.Builder
    fun layoutNotify(manger: NotificationManager){
        //为了版本兼容  选择V7包下的NotificationCompat进行构造
        builder = NotificationCompat.Builder(this@RemoteActivity,"1")
        //可以点击通知栏的删除按钮删除
        builder.setAutoCancel(true)
        //系统状态栏显示的小图标
        builder.setSmallIcon(R.mipmap.ic_launcher)
        //下拉显示的大图标
        //builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.balance));
        var intent = Intent(this@RemoteActivity,LoginActivity().javaClass)
        var pIntent = PendingIntent.getActivity(this,1,intent,0)

        var remoteViews = RemoteViews(packageName,R.layout.notificationlayout)
        remoteViews.setTextViewText(R.id.titlename,"notificationlayout")
        builder.setCustomContentView(remoteViews)
        builder.setContentIntent(pIntent)
        //通知默认的声音 震动 呼吸灯
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        var notification = builder.build()
        manger.notify(2,notification)
    }
}
