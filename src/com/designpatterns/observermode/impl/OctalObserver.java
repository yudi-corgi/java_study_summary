package com.designpatterns.observermode.impl;

import com.designpatterns.observermode.Observer;
import com.designpatterns.observermode.Subject;

/**
 * 八进制观察者
 * @author YUDI
 * @date 2020/8/31 23:15
 */
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("八进制输出："+Integer.toOctalString(subject.getState()));
    }
}
