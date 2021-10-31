package com.designpatterns.strategymode.impl;

import com.designpatterns.strategymode.StatusCodeStrategy;

/**
 * 状态码具体策略类：默认处理
 * @author YUDI
 * @date 2020/8/15 17:58
 */
public class DefaultProcess implements StatusCodeStrategy {

    @Override
    public void process(String msg) {
        System.out.println("默认处理：" + msg + "!");
    }
}
