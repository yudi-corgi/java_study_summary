package com.designpatterns.proxymode.staticproxy;

/**
 * @author YUDI
 * @date 2020/3/12 18:04
 * 聚合代理类
 */
public class CarAggregationAgent implements Moveable{

    private Car car;

    public CarAggregationAgent(Car car) {
        this.car = car;
    }

    @Override
    public void move() {

        //与继承代理区别就是：通过调用被代理对象来实现增加服务或其它操作
        long start = System.currentTimeMillis();
        System.out.println("开始行驶!");

        //被代理对象的业务方法
        car.move();

        long end = System.currentTimeMillis();
        System.out.println("结束行驶，耗时：" + (end-start) + "毫秒!");
    }


    public static void main(String[] args) {
        CarAggregationAgent caa = new CarAggregationAgent(new Car());
        caa.move();
    }
}
