package com.designpatterns.observermode;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者（主题）
 * @author YUDI
 * @date 2020/8/31 23:12
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public void notifyAllObserver() {
        observers.forEach(Observer::update);
    }

}
