package com.factorymode.factorymethod;

import com.factorymode.product.Car;

/**
 * @author YUDI
 * @date 2020/3/25 10:40
 * 抽象工厂方法
 */
public interface CarFactory {

    public Car createCar();

}
