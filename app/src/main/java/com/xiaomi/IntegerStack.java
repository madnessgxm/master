package com.xiaomi;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoxiaomeng on 2019/4/14.
 * 实现类
 */

public class IntegerStack extends Stack {
    private static String TAG = IntegerStack.class.getSimpleName();
    private int count = 0;

    public IntegerStack(boolean aotuEx) {
        super(20, aotuEx, 10);
    }

    public IntegerStack(int maxSize, boolean aotuEx) {
        super(maxSize, aotuEx, 10);
    }

    public IntegerStack(int maxSize, boolean aotuEx, int initsize) {
        super(maxSize, aotuEx, initsize);
    }

    @Override
    public synchronized void push(Object object) {
        //如果小于初始化直接入栈 ，否则判断其它参数
        Log.d(TAG, "push count = " + count);
        Log.d(TAG, "stacklist size = " + stackLst.length);
        if (count < initSize) {
            stackLst[count] = object;
            count++;
        } else {
            if (initSize == maxSize) {
                Log.d(TAG, "ArrayIndexOutOfBoundsException initsize =" + initSize);
                throw new ArrayIndexOutOfBoundsException("已经超过最大空间");
            }

            //可以扩展，栈内个数大于最大数，直接报异常
            if (count >= maxSize) {
                Log.d(TAG, "ArrayIndexOutOfBoundsException initsize =" + maxSize);
                throw new ArrayIndexOutOfBoundsException("已经超过最大空间");
            }
            //是否可以扩展
            if (!aotuEx) {
                stackLst[count] = object;
                count++;
            } else {
                //对于初始化栈进行扩展操作
                //当栈值数据大于现有空间栈数
                if (count >= stackLst.length) {
                    Integer[] mInteger = new Integer[stackLst.length];
                    System.arraycopy(stackLst, 0, mInteger, 0, stackLst.length);
                    if ((count + initSize) > maxSize) {
                        stackLst = new Integer[maxSize];
                    } else {
                        stackLst = new Integer[count + initSize];
                    }
                    Log.d(TAG, "aotuEx aotuex =" + (stackLst.length - mInteger.length));
                    System.arraycopy(mInteger, 0, stackLst, 0, mInteger.length);
                }
                stackLst[count] = object;
                count++;
            }
        }
    }

    @Override
    public synchronized Object pop() {

        Log.d(TAG, "pop begin" + count + "");
        //空栈，直接出，异常
        if (count == 0) {
            Log.d(TAG, "ArrayIndexOutOfBoundsException count =" + count);
            throw new ArrayIndexOutOfBoundsException("堆栈中没有数据");
        }
        count--;
        Object ret = stackLst[count];
        //如果现在栈的空间大于初始值，刚进行判断是否进行内存回收
        if (count == initSize) {
            Integer[] mInteger = new Integer[initSize];
            System.arraycopy(stackLst, 0, mInteger, 0, initSize);
            stackLst = mInteger;
        } else if (stackLst.length - count > initSize) {

            //判断现在栈的大小与最大栈值是否等
            //判断最大空间是否是实始化空间的整数倍
            if (stackLst.length == maxSize && stackLst.length % initSize != 0) {
                int lostarr = maxSize % initSize;
                if (stackLst.length - count > lostarr) {
                    Integer[] mInteger = new Integer[stackLst.length - lostarr];
                    System.arraycopy(stackLst, 0, mInteger, 0, initSize);
                    stackLst = mInteger;
                }
            } else {
                if (stackLst.length - count > initSize) {
                    Integer[] mInteger = new Integer[stackLst.length - initSize];
                    System.arraycopy(stackLst, 0, mInteger, 0, initSize);
                    stackLst = mInteger;
                }
            }
        }
        return ret;
    }

}
