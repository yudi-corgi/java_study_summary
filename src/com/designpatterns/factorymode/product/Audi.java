package com.designpatterns.factorymode.product;

/**
 * @author YUDI
 * @date 2020/3/25 9:31
 */
public class Audi implements Car {

    @Override
    public void drive() {
        System.out.println("奥迪四环。");
    }
}
