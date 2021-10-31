package com.designpatterns.templatemode.impl;

import com.designpatterns.templatemode.Game;

/**
 * 具体模板类：战争机器
 * @author YUDI
 * @date 2020/8/15 22:45
 */
public class GearGame extends Game {

    @Override
    public void doStart() {
        System.out.println("游戏开始，在兽族的攻击下存活吧.");
    }

    /**
     * 重写父类的钩子方法
     */
    @Override
    public void doEnd() {
        System.out.println("谜团不断，灾难接踵而至.");
    }
}
