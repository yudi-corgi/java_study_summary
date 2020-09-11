package com.designpatterns.visitormode;

import com.designpatterns.visitormode.impl.*;

/**
 * 访问者模式测试
 * @author CDY
 * @date 2020/9/10
 */
public class AllTest {

    public static void main(String[] args) {
        AccountBook ab = new AccountBook();
        //添加两条收入
        ab.add(new IncomeBill(10000, "卖商品"));
        ab.add(new IncomeBill(12000, "卖广告位"));
        //添加两条消费
        ab.add(new ConsumeBill(1000, "薪资"));
        ab.add(new ConsumeBill(2000, "材料费"));

        //创建访问者
        Boss boss = new Boss();
        CFO cfo = new CFO();
        CPA cpa = new CPA();

        //为账本设置访问者
        ab.show(boss);
        ab.show(cfo);
        ab.show(cpa);

        // boss访问账单
        boss.getTotalConsume();
        boss.getTotalIncome();

    }

}
