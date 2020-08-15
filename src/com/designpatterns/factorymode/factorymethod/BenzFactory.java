package com.designpatterns.factorymode.factorymethod;

import com.designpatterns.factorymode.product.Benz;
import com.designpatterns.factorymode.product.Car;

/**
 * @author YUDI
 * @date 2020/3/25 10:45
 */
public class BenzFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Benz();
    }
}
