package com.designpatterns.buildermode.impl;

import com.designpatterns.buildermode.product.Robot;
import com.designpatterns.buildermode.RobotBuilder;

/**
 * 具体建造者：攻击性机器人
 * @author YUDI
 * @date 2020/8/15 21:53
 */
public class AttackRobotBuilder implements RobotBuilder {

    private Robot robot;

    public AttackRobotBuilder() {
        this.robot = new Robot();
    }

    @Override
    public void builderHead() {
        robot.setHead("仿生头部");
    }

    @Override
    public void builderBody() {
        robot.setBody("振金制身体");
    }

    @Override
    public void builderHand() {
        robot.setHand("武装肩部飞弹和隐藏式手臂镭射炮");
    }

    @Override
    public void builderFoot() {
        robot.setFoot("搭载脚底喷气式飞行");
    }

    @Override
    public Robot createRobot() {
        return robot;
    }
}
