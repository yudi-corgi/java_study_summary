package com.designpatterns.visitormode;

import com.designpatterns.visitormode.impl.ConsumeBill;
import com.designpatterns.visitormode.impl.IncomeBill;

/**
 * 抽象访问者：主要是为了划分层次，针对消费和收入不同操作的访问者可以继承该类
 * 若消费收入操作逻辑相同，可仅仅实现 Visitor 接口编写统一操作逻辑
 * @author CDY
 * @date 2020/9/10
 */
public abstract class AbstractVisitor implements Visitor{

    /**
     * 访问消费账单
     * @param consumeBill 消费
     */
    public abstract void visit(ConsumeBill consumeBill);

    /**
     * 访问收入账单
     * @param incomeBill 收入
     */
    public abstract void visit(IncomeBill incomeBill);


    @Override
    public void visitAbstractVisitor(AbstractBill bill) {
        //缺省实现
    }
}
