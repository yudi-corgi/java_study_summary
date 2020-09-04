package com.designpatterns.decoratormode.impl;

import com.designpatterns.decoratormode.Shape;

/**
 * 具体组件：长方形
 * @author YUDI
 * @date 2020/9/4 22:35
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape：Rectangle.");
    }
}
