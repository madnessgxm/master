package com.emvl3kt;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.toolutils.view.AppMsg;
import com.toolutils.view.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class languageActivity extends BaseActivity {

    private static final String TAG =languageActivity.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);


        Button bt =  (Button) this.findViewById(R.id.prev_btn);
        bt.setTypeface(Typeface.createFromAsset(getAssets(),"font/thai.ttf"));
        Button bt1 =  (Button) this.findViewById(R.id.prev_btn1);
        bt1.setTypeface(Typeface.createFromAsset(getAssets(),"font/NotoSansThai-Regular.ttf"));
        Button bt2 =  (Button) this.findViewById(R.id.prev_btn2);
        bt2.setTypeface(Typeface.createFromAsset(getAssets(),"font/NotoSansThaiUI-Regular.ttf"));
        Typeface typeface =  bt2.getTypeface() ;

        final Button bt3 =  (Button) this.findViewById(R.id.prev_btn3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> list = new ArrayList<>();
                list.add("你好");
                list.add("你好,1");
                list.add("你好,2");
                list.add("你好,3");
                list.add("你好,4");
                ToastUtil.showInfo(languageActivity.this,list);
               /* bt3.setTypeface(bt3.getTypeface());
                bt3.setText(getResources().getString(R.string.keyboard_layout_picker_title));
               Typeface typeface =  bt3.getTypeface();

               Log.d(TAG,typeface.toString());*/
            }
        });
    }
}
