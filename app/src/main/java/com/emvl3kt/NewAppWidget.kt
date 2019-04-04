package com.emvl3kt

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {

    var click_action:String = "com.action.click"
    var tag:String  = "selfWidget"

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        Log.d("tag"," onEnabled ")
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        Log.d("tag"," onDisabled ")
        // Enter relevant functionality for when the last widget is disabled
    }


    companion object {
        var views:RemoteViews?=null
        var textviewstr :String?="123456"
        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            Log.d("tag"," updateAppWidget ")
            Log.d("tag"," updateAppWidget "+context.toString())
            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            views = RemoteViews(context.packageName, R.layout.new_app_widget)
            Log.e("tag"," updateAppWidget "+ context.packageName)
            Log.e("tag"," updateAppWidget "+ appWidgetId.toString())
            views!!.setTextViewText(R.id.appwidget_text, textviewstr)
            var intent = Intent(context,LoginActivity().javaClass)
            var pendingIntent = PendingIntent.getActivity(context,1,intent,FLAG_ONE_SHOT)
            views!!.setOnClickPendingIntent(R.id.appwidget_text,pendingIntent)
            // Instruct the widget manager to update the widget
            var componentName = ComponentName(context,NewAppWidget.javaClass)
            appWidgetManager.bindAppWidgetIdIfAllowed(appWidgetId,componentName)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("tag"," OnReceive "+intent!!.action.toString())
        if(intent!!.action == click_action)
        {
                //views = RemoteViews(context!!.packageName,R.layout.new_app_widget)
                Log.e("tag"," OnReceive "+ click_action)
                views!!.setTextViewText(R.id.appwidget_text,"hello appwidget")
                textviewstr = "hello appwidget"
                Log.e("tag"," OnReceive "+ context!!.packageName)
                var componentName = ComponentName(context,com.emvl3kt.NewAppWidget().javaClass)
                var widgetID = intent.extras.getIntArray(click_action)
                //var widgetID = AppWidgetManager.getInstance(context).getAppWidgetIds(componentName)
                //Log.e("tag"," OnReceive "+ widgetID.toString())

                if(widgetID==null)
                {
                    Log.e("tag"," OnReceive widgetid null")
                }
                Log.e("tag"," OnReceive widgetid "+widgetID.size.toString())
                for(i in widgetID)
                {
                    Log.e("tag"," OnReceive widgetid "+i.toString())
                }
                AppWidgetManager.getInstance(context).updateAppWidget(componentName, views)

        }else {
            super.onReceive(context, intent)
        }
    }
}

