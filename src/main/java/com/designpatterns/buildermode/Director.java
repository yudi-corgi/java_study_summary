package com.designpatterns.buildermode;

import com.designpatterns.buildermode.product.Robot;

/**
 * 指导者：根据传递的建造者创建机器人(Robot)
 * @author YUDI
 * @date 2020/8/15 22:01
 */
public class Director {

    public static Robot createRobot(RobotBuilder rb){
        rb.builderHead();
        rb.builderBody();
        rb.builderHand();
        rb.builderFoot();
        return rb.createRobot();
    }
}
