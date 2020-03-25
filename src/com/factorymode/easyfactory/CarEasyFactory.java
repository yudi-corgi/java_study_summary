package com.factorymode.easyfactory;

import com.factorymode.product.Audi;
import com.factorymode.product.Benz;
import com.factorymode.product.Bmw;
import com.factorymode.product.Car;

/**
 * @author YUDI
 * @date 2020/3/25 9:29
 * 简单工厂
 */
public class CarEasyFactory {

    /**
     * 创建产品对象的业务方法
     * @param type
     * @return
     */
    public static Car create(String type){

        switch(type){
            case "Benz":return new Benz();
            case "Bmw":return new Bmw();
            default:return new Audi();
        }

    }

}
