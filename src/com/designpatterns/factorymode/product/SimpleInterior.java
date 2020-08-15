package com.designpatterns.factorymode.product;

/**
 * @author YUDI
 * @date 2020/3/25 11:07
 * Interior 具体产品类
 */
public class SimpleInterior implements Interior {

    @Override
    public void description() {
        System.out.println("汽车内饰简单、极简款式！");
    }
}
