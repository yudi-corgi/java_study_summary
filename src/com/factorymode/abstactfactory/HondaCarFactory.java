package com.factorymode.abstactfactory;

import com.factorymode.product.Engine;
import com.factorymode.product.HondaEngine;
import com.factorymode.product.Interior;
import com.factorymode.product.SimpleInterior;

/**
 * @author YUDI
 * @date 2020/3/25 11:14
 * 具体工厂类，针对某个产品族或多个产品等级结构来创建复杂对象
 */
public class HondaCarFactory implements CarAbstractFactory{

    @Override
    public Engine createEngine() {
        return new HondaEngine();
    }

    @Override
    public Interior createInterior() {
        return new SimpleInterior();
    }
}
