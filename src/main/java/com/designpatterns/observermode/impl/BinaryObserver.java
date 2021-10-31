package com.designpatterns.observermode.impl;

import com.designpatterns.observermode.Observer;
import com.designpatterns.observermode.Subject;

/**
 * 二进制观察者
 * @author YUDI
 * @date 2020/8/31 23:15
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("二进制输出："+Integer.toBinaryString(subject.getState()));
    }
}
