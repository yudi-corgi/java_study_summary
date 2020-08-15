package com.designpatterns.facademode.impl;

import com.designpatterns.facademode.KfcSystem;

/**
 * @author YUDI
 * @date 2020/8/15 22:34
 */
public class BuyFrenchFries implements KfcSystem {
    @Override
    public void buy() {
        System.out.println("购买薯条.");
    }
}
