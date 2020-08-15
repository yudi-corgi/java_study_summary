package com.designpatterns.templatemode;

/**
 * 游戏抽象模板
 * @author YUDI
 * @date 2020/8/15 22:40
 */
public abstract class Game {

    //具体方法：默认实现，子类可根据需要重写该方法
    public void init(){
        System.out.println("初始化游戏中...");
    }

    public abstract void doStart();

    // 钩子方法：缺省实现，由子类扩展
    public void doEnd(){ }

}



