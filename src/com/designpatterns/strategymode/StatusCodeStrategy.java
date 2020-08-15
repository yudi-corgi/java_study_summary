package com.designpatterns.strategymode;

/**
 * 状态码响应抽象策略
 * @author YUDI
 * @date 2020/8/15 17:41
 */
public interface StatusCodeStrategy {

    public void process(String msg);
}
