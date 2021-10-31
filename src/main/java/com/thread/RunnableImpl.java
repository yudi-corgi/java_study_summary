package com.thread;

/**
 * Runnable 接口实现类
 * @author YUDI
 * @date 2020/5/18 15:38
 */
public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        int a = 0;
        for(int i=0;i<100;i++){
            a += i;
        }
        System.out.println(Thread.currentThread().getName() + ":" + a);
    }
}
