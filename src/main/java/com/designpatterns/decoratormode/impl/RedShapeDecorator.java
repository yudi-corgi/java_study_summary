package com.designpatterns.decoratormode.impl;

import com.designpatterns.decoratormode.Shape;
import com.designpatterns.decoratormode.ShapeDecorator;

/**
 * 具体装饰者：为图形画红色
 * @author YUDI
 * @date 2020/9/4 22:36
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    @Override
    public void draw() {
        decoratorShape.draw();

    }

    private void setRedBorder(Shape decoratorShape){
        System.out.println("Border Color：Red.");
    }

}
