package com.designpatterns.visitormode.impl;

import com.designpatterns.visitormode.AbstractVisitor;

/**
 * 具体访问者：老板，作用于抽象访问者层次
 * @author CDY
 * @date 2020/9/10
 */
public class Boss extends AbstractVisitor {

    private double totalIncome;
    private double totalConsume;

    /**
     * Boss 只查看总消费
     */
    @Override
    public void visit(ConsumeBill consumeBill) {
        totalConsume += consumeBill.getAmount();
    }

    /**
     * Boss 只查看总收入
     * @param incomeBill 收入
     */
    @Override
    public void visit(IncomeBill incomeBill) {
        totalIncome += incomeBill.getAmount();
    }

    public void getTotalConsume(){
        System.out.println("老板查看总消费："+totalConsume);
    }

    public void getTotalIncome(){
        System.out.println("老板查看总收入："+totalIncome);
    }
}
