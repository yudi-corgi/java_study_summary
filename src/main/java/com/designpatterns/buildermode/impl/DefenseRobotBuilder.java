package com.designpatterns.buildermode.impl;

import com.designpatterns.buildermode.product.Robot;
import com.designpatterns.buildermode.RobotBuilder;

/**
 * 具体建造者：防御型机器人
 * @author YUDI
 * @date 2020/8/15 22:00
 */
public class DefenseRobotBuilder implements RobotBuilder {

    private Robot robot;

    public DefenseRobotBuilder() {
        this.robot = new Robot();
    }

    @Override
    public void builderHead() {
        robot.setHead("封闭式防弹头罩");
    }

    @Override
    public void builderBody() {
        robot.setBody("超厚度钢铁身体");
    }

    @Override
    public void builderHand() {
        robot.setHand("配备火焰喷射与重锤");
    }

    @Override
    public void builderFoot() {
        robot.setFoot("驱动式多功能转换支撑");
    }

    @Override
    public Robot createRobot() {
        return robot;
    }
}
