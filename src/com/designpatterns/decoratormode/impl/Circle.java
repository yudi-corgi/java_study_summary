package com.designpatterns.decoratormode.impl;

import com.designpatterns.decoratormode.Shape;

/**
 * 具体组件：圆形
 * @author YUDI
 * @date 2020/9/4 22:34
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape：Circle.");
    }
}
