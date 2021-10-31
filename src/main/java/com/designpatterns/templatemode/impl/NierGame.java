package com.designpatterns.templatemode.impl;

import com.designpatterns.templatemode.Game;

/**
 * 具体模板类：尼尔
 * @author YUDI
 * @date 2020/8/15 22:43
 */
public class NierGame extends Game {
    @Override
    public void doStart() {
        System.out.println("游戏开始，操作 2B 小姐姐在无人的世界游玩吧.");
    }

    /**
     * 重写父类的钩子方法
     */
    @Override
    public void doEnd() {
        System.out.println("一切的存在都是为了被毁灭.");
    }
}
