package com.designpatterns.strategymode.impl;

import com.designpatterns.strategymode.NumericalOperationStrategy;

/**
 * 数值操作具体策略类：负责两数相加
 * @author YUDI
 * @date 2020/8/15 17:34
 */
public class StrategyAdd implements NumericalOperationStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
