package com.designpatterns.strategymode;

import com.designpatterns.strategymode.context.StatusCodeContext;
import com.designpatterns.strategymode.context.StrategyContext;
import com.designpatterns.strategymode.impl.StrategyAdd;
import com.designpatterns.strategymode.impl.StrategySubtract;

/**
 * 策略模式测试
 * @author YUDI
 * @date 2020/8/15 17:37
 */
public class AllTest {

    public static void main(String[] args) {

        System.out.println("简单使用：一般是用户传递条件，在环境类进行条件判断，但该方式没有触发这种情况.");
        StrategyContext sc1 = new StrategyContext(new StrategyAdd());
        System.out.println("数值相加:" + sc1.executeStrategy(2, 3));
        StrategyContext sc2 = new StrategyContext(new StrategySubtract());
        System.out.println("数值相减:" + sc2.executeStrategy(3, 2));

        System.out.println("解决 if...else 过多情况：将条件的判断交由环境类.");
        StatusCodeStrategy scs = StatusCodeContext.getInstance(200);
        scs.process("请求处理成功!");
        scs = StatusCodeContext.getInstance(302);
        scs.process("暂无此状态码的处理方式!");
    }

}
