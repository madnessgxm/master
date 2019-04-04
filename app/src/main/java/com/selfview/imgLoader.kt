package com.selfview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Environment
import android.util.LruCache
import android.widget.ImageView
import android.widget.ListView
import com.jakewharton.disklrucache.DiskLruCache
import java.io.File
import java.net.HttpURLConnection
import java.net.URL


class imgLoader {

    constructor(listView: ListView?) {
        mListView = listView
        mTask = HashSet<IamgeLoaderTask>()
        //最后占应用内存的1／8
        var cacheSize = Runtime.getRuntime().maxMemory() / 8
        mCache = LruCache<String, Bitmap>(cacheSize.toInt())
        imgCacheimg = HashMap<String,Bitmap>()
        cachePath = Environment.MEDIA_MOUNTED

        var file = getDiskCacheDir(listView!!.context,"bitmap")
        diskLruCache = DiskLruCache.open(file,1,1,10)
    }



    fun  getDiskCacheDir(context: Context, uniqueName:String ) : File {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath()
        } else {
            cachePath = context.getCacheDir().getPath()
        }
        return  File(cachePath + File.separator + uniqueName)
    }

    companion object {
        private var cachePath:String=""
        private var imgCacheimg: HashMap<String, Bitmap>? = null
        private var mListView: ListView? = null
        private var mTask: HashSet<IamgeLoaderTask>? = null
        private var mCache: LruCache<String, Bitmap>? = null
        private var diskLruCache :DiskLruCache?=null
    }

    fun show(imgview: ImageView, url: String) {
        var imgView = mListView!!.findViewWithTag<ImageView>(url)
        //判断磁盘软件是否为空
        var  snapshot = diskLruCache!!.get(url)
        if(snapshot!=null)
        {
            val bitmapis = snapshot.getInputStream(0)
            val bitmap = BitmapFactory.decodeStream(bitmapis)
            imgView.setImageBitmap(bitmap)
            return
        }
        if (imgCacheimg!!.get(url) == null) {
            var imgLoader = IamgeLoaderTask(imgview.tag.toString(),url)
            imgLoader.execute()
        } else {
            imgView.setImageBitmap(imgCacheimg!!.get(url))
        }
    }

    fun show(urls: Array<String>?, start: Int, end: Int) {
        for (i in start..end) {
            var imageUrl = urls!!.get(i)
            var imageView = mListView!!.findViewWithTag(imageUrl) as ImageView
            if(imageView!=null)
                show(imageView,imageUrl)
           /* imageView.setImageBitmap(bitmap)
            //从缓存中取图片
            var bitmap = imgCacheimg!!.get(imageUrl)
            //如果缓存中没有，则去下载
            if (bitmap == null) {
                var imgLoader = IamgeLoaderTask(imageUrl)
                imgLoader.execute()
                mTask!!.add(imgLoader)
            } else {

            }*/
        }
    }

    fun cancelAllTask() {
        if (mTask != null) {
            mTask!!.forEach {
                it.cancel(false)
            }
        }
    }

    class IamgeLoaderTask : AsyncTask<Void, Void, Bitmap> {
        var imgUrl: String? = null
        var imgKey :String?=null

        constructor(imgurl: String,imgkey:String) {
            imgUrl = imgurl
            imgKey = imgkey
        }

        override fun doInBackground(vararg params: Void?): Bitmap? {
            var bitmap = getBitmapByImageUrl(imgUrl!!)
            if (bitmap != null) {
                if (mCache!!.get(imgKey) == null)
                    mCache!!.put(imgKey, bitmap)
            }
            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            var imgview = mListView!!.findViewWithTag<ImageView>(imgKey)
            if (imgview != null)
                imgview.setImageBitmap(result)
            mCache!!.remove(imgKey)
        }

        /**
         * 根据图片路径下载图片Bitmap
         *
         * @param imageUrl 图片网络路径
         * @return
         */
        fun getBitmapByImageUrl(imageUrl: String): Bitmap? {
            var bitamp: Bitmap? = null
            var con: HttpURLConnection? = null
            try {
                var url = URL(imageUrl)
                con = url.openConnection() as HttpURLConnection
                con.setConnectTimeout(10 * 1000)
                con.setReadTimeout(10 * 1000)
                bitamp = BitmapFactory.decodeStream(con.getInputStream())
              /*  if(diskLruCache!!.get(imgKey)==null)
                {
                    diskinput = disk
                }*/
                var diskinput =  diskLruCache!!.edit(imgKey) as  DiskLruCache.Editor
                if(diskinput!=null) {

                    var outs = diskinput!!.newOutputStream(0)
                    var b = con!!.inputStream.read()
                    if (b != -1) {
                        while (b != -1) {
                            outs.write(b)
                            b = con.inputStream.read()
                        }
                        diskinput!!.commit()
                    } else {
                        diskinput!!.abort()
                    }
                }
            }
            catch (ex:Exception)
            {
                ex.printStackTrace()
            }
            finally {
                if (con != null) {
                    con.disconnect();
                }
            }
            return bitamp
        }
    }
}