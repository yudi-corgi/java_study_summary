package com.factorymode.product;

/**
 * @author YUDI
 * @date 2020/3/25 11:02
 * Engine 具体实现类
 */
public class HondaEngine implements Engine {

    @Override
    public void performance() {
        System.out.println("本田VTEC系统制作的发动机持久耐用，不错！");
    }
}
