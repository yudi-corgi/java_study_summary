package com.designpatterns.facademode;

import com.designpatterns.facademode.impl.BuyCoke;
import com.designpatterns.facademode.impl.BuyFrenchFries;
import com.designpatterns.facademode.impl.BuyHamburg;

/**
 * 外观类，包装子系统(KfcSystem)的多个功能，形成一个高级接口供客户端调用
 * @author YUDI
 * @date 2020/8/15 22:34
 */
public class Facade {

    private KfcSystem hamburg = new BuyHamburg();
    private KfcSystem frenchFries = new BuyFrenchFries();
    private KfcSystem coke = new BuyCoke();

    public void setMealOne(){
        System.out.println("套餐一：汉堡和可乐");
        hamburg.buy();
        coke.buy();
    }

    public void setMealTwo(){
        System.out.println("套餐二：薯条和可乐");
        frenchFries.buy();
        coke.buy();
    }

    public void setMealThree(){
        System.out.println("套餐三：汉堡、薯条、可乐");
        hamburg.buy();
        frenchFries.buy();
        coke.buy();
    }

}
