package com.factorymode.abstactfactory;

import com.factorymode.product.Engine;
import com.factorymode.product.Interior;

/**
 * @author YUDI
 * @date 2020/3/25 11:11
 * 抽象工厂类，针对多个产品等级结构来构建
 */
public interface CarAbstractFactory {

    public Engine createEngine();

    public Interior createInterior();

}
