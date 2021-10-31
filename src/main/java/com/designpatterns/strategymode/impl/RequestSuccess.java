package com.designpatterns.strategymode.impl;

import com.designpatterns.strategymode.StatusCodeStrategy;

/**
 * 状态码具体策略类：请求成功
 * @author YUDI
 * @date 2020/8/15 17:44
 */
public class RequestSuccess implements StatusCodeStrategy {

    @Override
    public void process(String msg) {
        System.out.println("2xx 状态码："+ msg +"!");
    }
}
