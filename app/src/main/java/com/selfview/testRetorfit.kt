package com.selfview

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface testRetorfit {
    @Headers("apikey:81bf9da930c7f9825a3c3383f1d8d766")
    @GET("word/word")
    fun getNews(@Query("num") num: String, @Query("page") page: String): Call<testRetorfit>
}