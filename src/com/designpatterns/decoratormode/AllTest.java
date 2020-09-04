package com.designpatterns.decoratormode;

import com.designpatterns.decoratormode.impl.Circle;
import com.designpatterns.decoratormode.impl.RainbowShapeDecorator;
import com.designpatterns.decoratormode.impl.Rectangle;
import com.designpatterns.decoratormode.impl.RedShapeDecorator;

/**
 * 装饰器模式测试
 * @author YUDI
 * @date 2020/9/4 22:32
 */
public class AllTest {

    public static void main(String[] args) {
        Shape c = new Circle();
        Shape r = new Rectangle();
        Shape red = new RedShapeDecorator(c);
        ShapeDecorator rainbow = new RainbowShapeDecorator(r);
        red.draw();
        rainbow.draw();
    }

}
