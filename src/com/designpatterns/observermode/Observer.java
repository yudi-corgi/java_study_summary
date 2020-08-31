package com.designpatterns.observermode;

/**
 * 抽象观察者
 * @author YUDI
 * @date 2020/8/31 23:11
 */
public abstract class Observer {

    public Subject subject;

    public abstract void update();

}
