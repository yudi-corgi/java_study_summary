package com.designpatterns.strategymode.context;

import com.designpatterns.strategymode.NumericalOperationStrategy;

/**
 * 数值操作环境类
 * @author YUDI
 * @date 2020/8/15 17:36
 */
public class StrategyContext {

    private NumericalOperationStrategy numericalOperationStrategy;
    public StrategyContext(NumericalOperationStrategy numericalOperationStrategy){
        this.numericalOperationStrategy = numericalOperationStrategy;
    }

    public int executeStrategy(int num1, int num2){
        return numericalOperationStrategy.doOperation(num1, num2);
    }

}
