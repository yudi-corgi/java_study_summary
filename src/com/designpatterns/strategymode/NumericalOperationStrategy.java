package com.designpatterns.strategymode;

/**
 * 数值操作抽象策略类
 * @author YUDI
 * @date 2020/8/15 17:34
 */
public interface NumericalOperationStrategy {

    public int doOperation(int num1, int num2);
}
