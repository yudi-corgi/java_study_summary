package com.designpatterns.adaptermode.impl;

import com.designpatterns.adaptermode.adapter.InterfaceAdapter;

/**
 * 继承接口适配器
 * @author YUDI
 * @date 2020/8/15 16:11
 */
public class SubTarget extends InterfaceAdapter {

    @Override
    public void two() {
        System.out.println("比如业务只需要用到 two() 方法.");
        System.out.println("就重写该方法即可.");
    }
}
