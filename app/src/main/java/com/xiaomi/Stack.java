package com.xiaomi;

import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by guoxiaomeng on 2019/4/14.
 * 基类
 */

public abstract class Stack {

    private static String TAG = Stack.class.getSimpleName();
    protected Object[] stackLst;

    protected int maxSize;      //最大值只有自动扩展时可用，只有可以自动扩展时有效
    protected boolean aotuEx;  //是否可以自动扩展
    protected int initSize;    //初始化大小

    public Stack(int mMaxSize,boolean mAotuEx,int mInitSize)
    {

        Log.d(TAG,"maxSize =  "+mMaxSize+ " InitSize= "+mInitSize+" maotuex="+mAotuEx+"");
        if(mInitSize<=0)
        {
            new Exception("参数错误 初始化空间不能为零");
        }
        //判断是否最大值小于初始化值
        if(mMaxSize<mInitSize)
        {
            new Exception("参数错误，最大值空间不为能小于初始化空间");
        }
        maxSize = mMaxSize;
        aotuEx = mAotuEx;
        initSize = mInitSize;
        stackLst = new Object[initSize];
    }

     //出栈
     public abstract Object pop();
    //进栈
     public abstract void push(Object object);
}
