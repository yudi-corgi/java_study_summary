package com.designpatterns.facademode;

/**
 * 外观模式测试
 * @author YUDI
 * @date 2020/8/15 22:38
 */
public class AllTest {

    public static void main(String[] args) {

        Facade f = new Facade();
        f.setMealOne();
        f.setMealTwo();
        f.setMealThree();
    }


}
