package com.factorymode.product;

/**
 * @author YUDI
 * @date 2020/3/25 9:27
 * 具体工厂类 - 实现Benz产品创建
 */
public class Benz implements Car {

    @Override
    public void drive() {
        System.out.println("开着奔驰彩旗飘飘。");
    }
}
