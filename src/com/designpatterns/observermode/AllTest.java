package com.designpatterns.observermode;

import com.designpatterns.observermode.impl.BinaryObserver;
import com.designpatterns.observermode.impl.HexaObserver;
import com.designpatterns.observermode.impl.OctalObserver;

/**
 * 观察者模式测试类
 * @author YUDI
 * @date 2020/8/31 23:49
 */
public class AllTest {

    public static void main(String[] args) {
        Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        new HexaObserver(subject);

        subject.setState(16);
        System.out.println("被观察者变化----------");
        subject.setState(22);

    }

}
