package com.RetrofitRxJavaOkHtto;

import com.httpProxy.weatherinfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IWeather {

    @GET("101010100.html")
    Observable<Weathinfo> getMsgHomeInfo();
}
