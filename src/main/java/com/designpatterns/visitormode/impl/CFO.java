package com.designpatterns.visitormode.impl;

import com.designpatterns.visitormode.AbstractBill;
import com.designpatterns.visitormode.Visitor;

/**
 * 具体访问者：财务主管，作用于接口访问层次
 * 模拟消费和收入的操作逻辑相同
 * 不需要继承抽象访问者独立实现消费、收入逻辑
 * @author CDY
 * @date 2020/9/10
 */
public class CFO implements Visitor {
    @Override
    public void visitAbstractVisitor(AbstractBill bill) {
        System.out.println("财务主管获取账单金额:"+ bill.getAmount() +"，事项："+bill.getName());
    }
}
