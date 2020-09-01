package com.designpatterns.chainormode.impl;

import com.designpatterns.chainormode.AbstractLogHandler;

/**
 * 具体处理者，日志界别 INFO
 * @author YUDI
 * @date 2020/9/1 21:52
 */
public class ConsoleLogHandler  extends AbstractLogHandler {

    public ConsoleLogHandler(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Console::log：" + message);
    }
}
