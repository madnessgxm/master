package com.emvl3kt;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class LineHeightActivity extends BaseActivity {

    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            linearLayout.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
        }
    };

    private LinearLayout linearLayout;
    private LinearLayout linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_height);

        linearLayout = findViewById(R.id.line1);
        linearLayout2 = findViewById(R.id.line2);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        handler.sendEmptyMessageAtTime(1,5000);
                    }
                }).start();
            }
        });

    }

}
