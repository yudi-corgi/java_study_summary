package com.designpatterns.proxymode.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author YUDI
 * @date 2020/3/12 20:13
 * 需要导入 cglib-nodep.jar
 * cglib 代理
 * 实现接口 MethodInterceptor 中的拦截方法 intercept()
 * 本意就是通过子类拦截父类的所有方法以达到代理作用来增加其它操作
 */
public class CglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    //获取代理类
    public Object getProxy(Class cls){
        //设置创建子类的目标类
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this);
        //返回代理类
        return enhancer.create();
    }

    /**
     * 拦截所有目标类(被代理类)方法的调用
     * @param o 目标类的示例
     * @param method    目标类的反射方法
     * @param args   方法参数
     * @param methodProxy   代理类实例
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        //其它业务操作..
        System.out.println("火车开始行驶！");

        //代理类调用父类的方法
        methodProxy.invokeSuper(o,args);

        System.out.println("火车结束行驶！");
        return null;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Train t = (Train) cglibProxy.getProxy(Train.class);
        t.move();
    }
}
