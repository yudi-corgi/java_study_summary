package com.proxymode.jdkproxy;

import com.proxymode.staticproxy.Car;
import com.proxymode.staticproxy.Moveable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author YUDI
 * @date 2020/3/12 19:08
 * JDK 动态代理，必须实现 InvocationHandler 接口
 * 时间记录代理
 */
public class TimeHandler implements InvocationHandler {

    private Object target;

    public TimeHandler(Object target) {
        this.target = target;
    }

    /**
     * 反射方法
     * @param proxy 代理类（也可指被代理类）
     * @param method 被代理的方法
     * @param args 方法的参数
     * @return 返回方法的执行结果
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //增加其它业务功能.. 此处简单展示记录汽车行驶时间
        long start = System.currentTimeMillis();
        System.out.println("开始行驶!");

        //此处使用传递过来的被代理对象，也就是反射调用其实现的方法
        method.invoke(target);

        long end = System.currentTimeMillis();
        System.out.println("结束行驶，耗时：" + (end-start) + "毫秒!");
        return "This is JDK Proxy!";
    }

    public static void main(String[] args) throws Throwable {

        //此处的 Car 对象使用的是静态代理内的Car对象
        Car car = new Car();
        Class<?> cls = car.getClass();
        InvocationHandler timeH = new TimeHandler(car);
        //生成代理类，参数一：被代理类的类加载器  二：被代理类的接口  三：事件处理器
        Moveable m = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),timeH);
        m.move();

        //多个代理嵌套
        InvocationHandler logH = new LogHandler(m);
        Class<?> cls2 = m.getClass();
        Moveable m2 = (Moveable) Proxy.newProxyInstance(cls2.getClassLoader(),cls2.getInterfaces(),logH);
        m2.move();

    }
}
