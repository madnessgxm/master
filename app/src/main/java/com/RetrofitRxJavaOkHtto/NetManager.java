package com.RetrofitRxJavaOkHtto;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManager {

    private static String URL = "http://www.weather.com.cn/data/sk/";
    private static Retrofit retrofit;
    public static <T> T createClass(Class<T> createClass)
    {
        if(retrofit == null){
            synchronized (NetManager.class){
                if(retrofit == null){
                    retrofit = new Retrofit.Builder().baseUrl(URL)
                            .client(getOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                }
            }
        }

       return retrofit.create(createClass);
    }

    private static OkHttpClient okHttpClient;
    private static OkHttpClient getOkHttpClient()
    {
        synchronized (NetManager.class)
        {
            if(okHttpClient==null)
            {
                okHttpClient = new OkHttpClient();

            }
        }
        return okHttpClient;
    }
}
