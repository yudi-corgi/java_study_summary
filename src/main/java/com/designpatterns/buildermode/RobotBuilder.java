package com.designpatterns.buildermode;

import com.designpatterns.buildermode.product.Robot;

/**
 * 抽象建造者
 * @author YUDI
 * @date 2020/8/15 21:50
 */
public interface RobotBuilder {

    void builderHead();
    void builderBody();
    void builderHand();
    void builderFoot();
    Robot createRobot();
}
