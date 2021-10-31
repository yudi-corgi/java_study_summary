package com.designpatterns.buildermode;

import com.designpatterns.buildermode.impl.AttackRobotBuilder;
import com.designpatterns.buildermode.impl.DefenseRobotBuilder;
import com.designpatterns.buildermode.product.House;
import com.designpatterns.buildermode.product.Robot;

/**
 * 建造者模式测试
 * @author YUDI
 * @date 2020/8/15 22:03
 */
public class AllTest {

    public static void main(String[] args) {

        System.out.println("常规实现：包含抽象/具体建造者、指导者、产品类");
        RobotBuilder attack = new AttackRobotBuilder();
        Robot attackRobot = Director.createRobot(attack);
        System.out.println(attackRobot.toString());
        RobotBuilder defense = new DefenseRobotBuilder();
        Robot defenseRobot = Director.createRobot(defense);
        System.out.println(defenseRobot.toString());

        System.out.println("去除指导者，在产品类创建静态内部类，灵活控制产品创建的细节");
        House house = new House.ConcreteBuilder()
                            .basic("打地基.")
                            .walls("砌墙.")
                            .roofed("封顶大吉.")
                            .createHouse();
        System.out.println(house.toString());
    }

}
