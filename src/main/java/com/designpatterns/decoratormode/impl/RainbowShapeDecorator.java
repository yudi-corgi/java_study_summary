package com.designpatterns.decoratormode.impl;

import com.designpatterns.decoratormode.Shape;
import com.designpatterns.decoratormode.ShapeDecorator;

/**
 * 具体装饰者：为图形画彩虹
 * @author YUDI
 * @date 2020/9/4 22:37
 */
public class RainbowShapeDecorator extends ShapeDecorator {

    public RainbowShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    @Override
    public void draw() {
        decoratorShape.draw();
    }

    //添加额外的功能
    private void setRainbowBorder(Shape decoratorShape){
        System.out.println("Border Color：Rainbow");
    }
}
