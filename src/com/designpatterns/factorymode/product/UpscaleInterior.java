package com.designpatterns.factorymode.product;

/**
 * @author YUDI
 * @date 2020/3/25 11:09
 * Interior 具体产品类
 */
public class UpscaleInterior implements Interior {

    @Override
    public void description() {
        System.out.println("汽车内饰高档配置、极致奢华！");
    }
}
