package com.designpatterns.facademode.impl;

import com.designpatterns.facademode.KfcSystem;

/**
 * @author YUDI
 * @date 2020/8/15 22:33
 */
public class BuyHamburg implements KfcSystem {
    @Override
    public void buy() {
        System.out.println("购买汉堡.");
    }
}
