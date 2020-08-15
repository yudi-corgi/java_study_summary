package com.designpatterns.buildermode;

import com.designpatterns.buildermode.product.House;

/**
 * 房子抽象类
 * @author YUDI
 * @date 2020/8/15 22:17
 */
public interface HouseBuilder {

    public HouseBuilder basic(String msg);
    public HouseBuilder walls(String msg);
    public HouseBuilder roofed(String msg);
    public House createHouse();

}
