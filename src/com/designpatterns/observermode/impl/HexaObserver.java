package com.designpatterns.observermode.impl;

import com.designpatterns.observermode.Observer;
import com.designpatterns.observermode.Subject;

/**
 * 十六进制观察者
 * @author YUDI
 * @date 2020/8/31 23:16
 */
public class HexaObserver extends Observer {


    public HexaObserver(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("十六进制输出："+Integer.toHexString(subject.getState()).toUpperCase());
    }
}
