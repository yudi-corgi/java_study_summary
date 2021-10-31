package com.designpatterns.visitormode.impl;

import com.designpatterns.visitormode.AbstractVisitor;

/**
 * 具体访问者：会计师，作用于抽象访问者层次
 * @author CDY
 * @date 2020/9/10
 */
public class CPA extends AbstractVisitor {


    @Override
    public void visit(ConsumeBill consumeBill) {
        if("薪资".equals(consumeBill.getName())){
            System.out.println("会计查看薪资数据.");
        }else{
            System.out.println("会计整理材料费用报表.");
        }
    }

    @Override
    public void visit(IncomeBill incomeBill) {
        System.out.println("会计查看所有收入和账单细节.");
    }
}
