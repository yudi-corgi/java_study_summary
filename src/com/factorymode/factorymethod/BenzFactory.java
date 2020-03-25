package com.factorymode.factorymethod;

import com.factorymode.product.Benz;
import com.factorymode.product.Car;

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
