package com.factorymode.product;

/**
 * @author YUDI
 * @date 2020/3/25 11:05
 * Engine 具体产品类
 */
public class BenzEngine implements Engine {

    @Override
    public void performance() {
        System.out.println("奔驰品牌系列的发动机也很好，故障率极低！");
    }
}
