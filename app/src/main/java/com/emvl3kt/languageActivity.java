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
import com.xml.XmlUtil;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
              /*  List<String> list = new ArrayList<>();
                list.add("你好");
                list.add("你好,1");
                list.add("你好,2");
                list.add("你好,3");
                list.add("你好,4");
                String str ="你好你好1你好2你好3";
                ToastUtil.showInfo(languageActivity.this,str);*/
              String tmpstr = "<root>" +"<tab>" +
                      "        <RY_Bh>01</RY_Bh>" +
                      "        <RY_Name>周扬升</RY_Name>" +
                      "        <BM>蜘蛛人</BM>" +
                      "        <icid>CA000001</icid>" +
                      "      </tab>" +
                      "      <tab>" +
                      "        <RY_Bh>02</RY_Bh>" +
                      "        <RY_Name>陈言翰</RY_Name>" +
                      "        <BM>蜘蛛人</BM>" +
                      "        <icid>CA000002</icid>" +
                      "      </tab>" +
                      "      <tab>" +
                      "        <RY_Bh>03</RY_Bh>" +
                      "        <RY_Name>廖秋铭</RY_Name>" +
                      "        <BM>蜘蛛人</BM>" +
                      "        <icid>CA000003</icid>" +
                      "      </tab>" +
                      "      <tab>" +
                      "        <RY_Bh>04</RY_Bh>" +
                      "        <RY_Name>许骥</RY_Name>" +
                      "        <BM>蜘蛛人</BM>" +
                      "        <icid>CA000004</icid>" +
                      "      </tab>" +
                      "      <tab>" +
                      "        <RY_Bh>05</RY_Bh>" +
                      "        <RY_Name>朱红源</RY_Name>" +
                      "        <BM>蜘蛛人</BM>" +
                      "        <icid>CA000005</icid>" +
                      "      </tab>"+
                      "</root>";

              try {
                  XmlUtil<CRY> xmlUtil = new XmlUtil<>();
                  List<CRY> cries =  xmlUtil.getLst(CRY.class,tmpstr);
                  Log.d(TAG,cries.size()+"") ;
              }catch (Exception ex)
              {
                  ex.printStackTrace();
              }

            }
        });
    }
}
