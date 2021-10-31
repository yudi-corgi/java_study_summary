package com.designpatterns.proxymode.staticproxy;

/**
 * @author YUDI
 * @date 2020/3/12 17:57
 * 代理类
 */
public class CarProxy extends Car {

    @Override
    public void move() {

        //增加其它业务功能.. 此处简单展示记录汽车行驶时间
        long start = System.currentTimeMillis();
        System.out.println("开始行驶!");

        //这是被代理类的方法
        super.move();

        long end = System.currentTimeMillis();
        System.out.println("结束行驶，耗时：" + (end-start) + "毫秒!");

    }


    public static void main(String[] args) {
        CarProxy cp = new CarProxy();
        cp.move();
    }

}
