package com.factorymode;

import com.factorymode.abstactfactory.BenzCarFactory;
import com.factorymode.abstactfactory.CarAbstractFactory;
import com.factorymode.abstactfactory.HondaCarFactory;
import com.factorymode.easyfactory.CarEasyFactory;
import com.factorymode.factorymethod.AudiFactory;
import com.factorymode.factorymethod.BmwFactory;
import com.factorymode.factorymethod.CarFactory;
import com.factorymode.product.Bmw;
import com.factorymode.product.Car;

/**
 * @author YUDI
 * @date 2020/3/25 9:32
 */
public class AllTest {

    public static void main(String[] args) {
        //简单工厂模式
        Car bmw1 = CarEasyFactory.create("Bmw");
        bmw1.drive();
        Car benz1 = CarEasyFactory.create("Benz");
        benz1.drive();

        //工厂方法模式
        CarFactory audiFactory = new AudiFactory();
        audiFactory.createCar().drive();
        CarFactory bmwFactory = new BmwFactory();
        Car bmw2 = bmwFactory.createCar();
        bmw2.drive();

        //抽象工厂模式
        CarAbstractFactory caf1 = new BenzCarFactory();
        caf1.createEngine().performance();
        caf1.createInterior().description();
        CarAbstractFactory caf2 = new HondaCarFactory();
        caf2.createEngine().performance();
        caf2.createInterior().description();

    }


}
