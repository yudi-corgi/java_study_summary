package com.designpatterns.chainormode.impl;

import com.designpatterns.chainormode.AbstractLogHandler;

/**
 * 具体处理者，日志级别 ERROR
 * @author YUDI
 * @date 2020/9/1 21:53
 */
public class ErrorLogHandler extends AbstractLogHandler {

    public ErrorLogHandler(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Error::Log：" + message);
    }
}
