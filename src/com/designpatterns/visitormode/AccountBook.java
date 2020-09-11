package com.designpatterns.visitormode;

import java.util.ArrayList;
import java.util.List;

/**
 * 结构对象：账本
 * @author CDY
 * @date 2020/9/10
 */
public class AccountBook {

    private List<Bill> billList = new ArrayList<>();

    /**
     * 添加单子
     * @param bill 单子
     */
    public void add(Bill bill){
        billList.add(bill);
    }

    /**
     * 所有账单接受一个访问者
     * @param visitor 访问者
     */
    public void show(Visitor visitor){
        for (Bill bill : billList) {
            bill.accept(visitor);
        }
    }

}
