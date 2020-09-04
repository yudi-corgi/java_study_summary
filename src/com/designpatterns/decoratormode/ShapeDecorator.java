package com.designpatterns.decoratormode;

/**
 * 抽象装饰者
 * @author YUDI
 * @date 2020/9/4 22:33
 */
public abstract class ShapeDecorator implements Shape {

    protected Shape decoratorShape;
    public ShapeDecorator(Shape decoratorShape) {
        this.decoratorShape = decoratorShape;
    }

    @Override
    public void draw() {
        decoratorShape.draw();
    }
}
