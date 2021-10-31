package com.designpatterns.factorymode.factorymethod;

import com.designpatterns.factorymode.product.Bmw;
import com.designpatterns.factorymode.product.Car;

/**
 * @author YUDI
 * @date 2020/3/25 10:48
 */
public class BmwFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Bmw();
    }
}
