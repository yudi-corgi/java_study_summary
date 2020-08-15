package com.designpatterns.proxymode.staticproxy;

import java.util.Random;

/**
 * @author YUDI
 * @date 2020/3/12 17:53
 * 被代理类
 */
public class Car implements Moveable {

    @Override
    public void move() {
        //模拟汽车行驶
        try {
            System.out.println("汽车行驶中.....");
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
