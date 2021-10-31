package com.designpatterns.strategymode.impl;

import com.designpatterns.strategymode.StatusCodeStrategy;

/**
 * 状态码具体策略类：服务器错误
 * @author YUDI
 * @date 2020/8/15 17:42
 */
public class ServerError implements StatusCodeStrategy {

    @Override
    public void process(String msg) {
        System.out.println("5xx 状态码："+ msg +"！");
    }
}
