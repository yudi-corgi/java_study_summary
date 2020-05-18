package com.thread;

import java.util.concurrent.Callable;

/**
 * Callable 接口实现，让 FutureTask 包装作为线程执行体
 * @author YUDI
 * @date 2020/5/18 17:27
 */
public class CallableImpl implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("Callable 创建的线程");
        return "创建成功!";
    }
}
