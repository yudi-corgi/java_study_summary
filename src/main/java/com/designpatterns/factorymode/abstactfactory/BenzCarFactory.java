package com.designpatterns.factorymode.abstactfactory;

import com.designpatterns.factorymode.product.BenzEngine;
import com.designpatterns.factorymode.product.Engine;
import com.designpatterns.factorymode.product.Interior;
import com.designpatterns.factorymode.product.UpscaleInterior;

/**
 * @author YUDI
 * @date 2020/3/25 11:12
 * 具体工厂类，针对某个产品族或多个产品等级结构来创建复杂对象
 */
public class BenzCarFactory implements CarAbstractFactory {

    @Override
    public Engine createEngine() {
        return new BenzEngine();
    }

    @Override
    public Interior createInterior() {
        return new UpscaleInterior();
    }
}
