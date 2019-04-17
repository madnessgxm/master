package com.xiaomi;

/**
 * Created by guoxiaomeng on 2019/4/14.
 * 测试案例类
 */

public class StackMain {

    private static Stack integerStack;
    public static void main(Object[] args)
    {
        /*try {
            integerStack = new IntegerStack(5, false, 10);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try {
            //2,初始空间为零
            integerStack = new IntegerStack(5,false,0);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try {
            //3,最大空间是初始化空间的整数倍
            integerStack = new IntegerStack(20, false, 10);
            integerStack = new IntegerStack(50, false, 10);
            integerStack = new IntegerStack(100, false, 10);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try {
            //4最大空间不是初始化空间的整数倍
            integerStack = new IntegerStack(13, false, 10);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }*/

        try {
            //5 入栈小于初始化栈空间
            integerStack = new IntegerStack(27, true, 20);
            for (int i = 0; i < 27; i++) {
                integerStack.push(i);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        System.out.print(integerStack.pop());
                        Thread.sleep(100);
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        integerStack.push(i + "");
                        Thread.sleep(100);
                    }
                }catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }).start();
       /* //6 正常出栈
        for(int i=0;i<20;i++)
        {
            integerStack.pop();
        }
        */

        /*//7 出栈个数大于入栈个数
        for(int i=0;i<6;i++)
        {
            integerStack.pop();
        }

        //8入栈个数大于初始个数
        for(int i=0;i<30;i++)
        {
            integerStack.push(i);
        }

        //9入栈一部分然后出栈（1）
        for(int i=0;i<15;i++)
        {
            integerStack.push(i);
        }

        for(int i=0;i<15;i++)
        {
            integerStack.pop();
        }

        //(2)入数等于出数
        for(int i=0;i<27;i++)
        {
            integerStack.push(i);
        }

        for(int i=0;i<27;i++)
        {
            integerStack.pop();
        }

        //(3) 先入一部分／然后出，出数量<入数量 ，再入
        for(int i=0;i<25;i++)
        {
            integerStack.push(i);
        }

        for(int i=0;i<20;i++)
        {
            integerStack.pop();
        }
        for(int i=0;i<5;i++)
        {
            integerStack.push(i);
        }

*/
    }

   /* //1.最大空间小于初始化空单
    public void init()
    {
        integerStack = new IntegerStack(5,false,10);
    }

    //1.最大空间小于初始化空单
    public void initsizezero()
    {
        integerStack =new IntegerStack(5,false,0);
    }*/

}
