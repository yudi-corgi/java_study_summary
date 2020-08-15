package com.designpatterns.proxymode.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author YUDI
 * @date 2020/3/12 19:39
 * 日志事件代理类
 */
public class LogHandler implements InvocationHandler {

    private Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("开始记录日志！");

        method.invoke(target);

        System.out.println("结束记录日志！");

        return "WTF!";
    }
}
