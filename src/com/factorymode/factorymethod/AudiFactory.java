package com.factorymode.factorymethod;

import com.factorymode.product.Audi;
import com.factorymode.product.Car;

/**
 * @author YUDI
 * @date 2020/3/25 10:47
 * 具体工厂类 - 实现Audi产品创建
 */
public class AudiFactory implements CarFactory {

    @Override
    public Car createCar() {
        return new Audi();
    }
}
