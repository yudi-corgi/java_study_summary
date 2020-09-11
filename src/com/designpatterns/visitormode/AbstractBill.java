package com.designpatterns.visitormode;

/**
 * 抽象元素：账单，即接口的再一次实现与封装
 * @author CDY
 * @date 2020/9/10
 */
public abstract class AbstractBill implements Bill{

    public double amount;
    public String name;

    public AbstractBill(double amount, String name) {
        this.amount = amount;
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
