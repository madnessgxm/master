package com.emvl3kt

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import com.selfview.Retorfitfactory
import com.selfview.lruCacheImage
import com.selfview.testRetorfit
import kotlinx.android.synthetic.main.activity_lru_cache_list_view.*
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LruCacheListViewActivity : BaseActivity() {

    var lrucacheImage:lruCacheImage?=null
    var mcallBack = LruCacheListViewActivity.mCallBack()
    var handler = Handler(mcallBack)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lru_cache_list_view)
        var url:Array<String> =arrayOf("https://pics1.baidu.com/feed/b03533fa828ba61ef380d79cec81e80e314e5954.png?token=95fe0981d4640f6a34c25d05b4a841ad&s=65B416D00B136BD666A85D5F030050F2",
                "https://pics0.baidu.com/feed/d31b0ef41bd5ad6e6d1caa0a2d7e46dfb4fd3ca7.png?token=bc857c6a650b38fe9558ff643417e1a0&s=639150824C604E942B9DA8A003007063",
                "https://pics6.baidu.com/feed/0b55b319ebc4b745bd1c548065496113888215d2.jpeg?token=6202310b5b5656c676ea1cf592d5cdc7&s=7A40698058031CDE5EDD2815030070C2" )
        lrucacheImage = lruCacheImage(this@LruCacheListViewActivity,url,lruImg_ListView)
        var viewhead =  LayoutInflater.from(this).inflate(R.layout.lstviewhead,null)
        var viewfoothead = LayoutInflater.from(this).inflate(R.layout.lstviewfoothead,null)
        lruImg_ListView.adapter =lrucacheImage
        lruImg_ListView.addHeaderView(viewhead)
        lruImg_ListView.addFooterView(viewfoothead)
        val retrofit = Retrofit.Builder()
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求的Url地址
                .baseUrl("http://apis.baidu.com/txapi/")
                .build()
// 创建网络请求接口的实例
       var  mApi = retrofit.create<testRetorfit>(testRetorfit::class.java!!) as testRetorfit
        mApi.getNews("","")

    }
    class mCallBack : Handler.Callback
    {
        override fun handleMessage(msg: Message?): Boolean {
            return false
        }
    }
}
