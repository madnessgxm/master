package com.usb;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.emvl3kt.BaseActivity;
import com.emvl3kt.R;
import com.facebook.common.util.Hex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import ui.wangpos.com.utiltool.HEXUitl;

public class USBActivity extends BaseActivity {

    private static final String TAG = USBActivity.class.getSimpleName();
    private ServerThread serverThread;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            String tmp = msg.getData().getString("MSG", "Toast");
            Toast.makeText(getApplicationContext(), tmp, Toast.LENGTH_SHORT).show();
            tvtips.setText(tmp);
        }
    };

    private TextView tvtips;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb);
        tvtips = this.findViewById(R.id.tvtips);
        btn = this.findViewById(R.id.btnSocket);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serverThread = new ServerThread();
                serverThread.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        serverThread.setIsLoop(false);
    }


    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private ServerRead serverRead;

    class ServerRead extends Thread {
        @Override
        public void run() {

            if (inputStream != null) {
                String firstStr = "";
                while (true) {
                    try {
                        byte[] bytes = new byte[128];
                        int ret = inputStream.read(bytes);
                        firstStr += HEXUitl.bytesToHex(bytes);
                        if (ret < 128) {
                            //处理读到数据
                            Log.d(TAG, firstStr);
                        }
                    } catch (Exception ex) {
                        Log.e(TAG, "读取超时....");
                        ex.printStackTrace();
                    }
                }
            }

        }
    }

    private long currentlong = 0;
    private int timeinterval = 30 * 1000;

    class ServerWrite extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    WriteString("json.getintervale");

                } catch (Exception ex) {

                }
            }
        }
    }

    public void WriteString(String str) throws Exception {
        if (outputStream != null) {
            outputStream.write(str.getBytes("utf8"));
        }
    }

    private ServerSocket serverSocket = null;
    private Socket socket = null;

    //开启服务器联接
    class ServerThread extends Thread {
        boolean isLoop = true;

        public void setIsLoop(boolean isLoop) {
            this.isLoop = isLoop;
        }

        @Override
        public void run() {
            Log.d(TAG, "running");
            try {
                serverSocket = new ServerSocket(9000);
                // while (isLoop) {
                Socket socket = serverSocket.accept();
                Log.d(TAG, "accept");
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
                serverRead = new ServerRead();
                serverRead.start();
                    /*while(isLoop){
                        //outputStream.writeUTF("test data");
                        Log.w("johnny","send data");
                        Thread.sleep(1000);
                        byte[] bytes = new byte[300];
                       int ret =  inputStream.read(bytes);
                       if(ret>0) {
                           Message msg = new Message();
                           Bundle bundle = new Bundle();
                           bundle.putString("MSG", new String(bytes, Charset.forName("utf-8")));
                           msg.setData(bundle);
                           handler.sendMessage(msg);
                       }
                    }
                    socket.close();*/
                //}
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Log.d(TAG, "destory");
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
