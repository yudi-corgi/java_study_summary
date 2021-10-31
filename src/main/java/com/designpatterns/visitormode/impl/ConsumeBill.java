package com.designpatterns.visitormode.impl;

import com.designpatterns.visitormode.AbstractBill;
import com.designpatterns.visitormode.AbstractVisitor;
import com.designpatterns.visitormode.Visitor;

/**
 * 具体元素：消费账单
 * @author CDY
 * @date 2020/9/10
 */
public class ConsumeBill extends AbstractBill {

    public ConsumeBill(double amount, String name) {
        super(amount,name);
    }

    @Override
    public void accept(Visitor visitor) {
        // 这一步是为了区分不同层次的访问者（抽象/接口访问者）
        if (visitor instanceof AbstractVisitor) {
            ((AbstractVisitor) visitor).visit(this);
            return;
        }
        visitor.visitAbstractVisitor(this);
    }

}
