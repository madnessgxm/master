package com.toolutils.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.toolutils.R;

import java.util.List;

public class ToastUtil {

	private static AppMsg appMsg;
	public static void showInfo(Activity context,String text){
		AppMsg.Style style = AppMsg.STYLE_ALERT;
		if(appMsg!=null)
		{
			appMsg.cancel();
		}
		appMsg = AppMsg.makeText(context, (CharSequence)text, style);
		appMsg.show();
	}

	private static AppMsg appMsglst;
	public static void showInfo(Activity context, List<String> text){
		AppMsg.Style style = new AppMsg.Style(AppMsg.LENGTH_SHORT*text.size()*10, R.color.alert);// AppMsg.STYLE_ALERT;
		if(appMsglst!=null)
		{
			appMsglst.cancel();
		}
		appMsglst = AppMsg.makeText(context, text, style);
		appMsglst.show();
	}

	public static void showNetError(Context context){
		Toast.makeText(context, "网络不给力,请稍后再试！", Toast.LENGTH_SHORT).show();
	}
	
	public static void showServerError(Context context){
		Toast.makeText(context, "服务器错误,请重试!", Toast.LENGTH_SHORT).show(); 
	}
}
