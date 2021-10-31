package com.designpatterns.chainormode;

import com.designpatterns.chainormode.impl.ConsoleLogHandler;
import com.designpatterns.chainormode.impl.ErrorLogHandler;
import com.designpatterns.chainormode.impl.FileLogHandler;

/**
 * 责任链模式：客户端
 * @author YUDI
 * @date 2020/9/1 21:54
 */
public class Client {

    /** 获取日志处理器责任链 */
    private static AbstractLogHandler getChainOfLogHandler(){
        AbstractLogHandler consoleLog = new ConsoleLogHandler(AbstractLogHandler.INFO);
        AbstractLogHandler fileLog = new FileLogHandler(AbstractLogHandler.DEBUG);
        AbstractLogHandler errorLog = new ErrorLogHandler(AbstractLogHandler.ERROR);
        errorLog.setSuccessor(fileLog);
        fileLog.setSuccessor(consoleLog);
        return errorLog;
    }

    public static void main(String[] args) {
        AbstractLogHandler logHandler = getChainOfLogHandler();
        logHandler.logMessage(AbstractLogHandler.INFO,"This is an information.");
        logHandler.logMessage(AbstractLogHandler.DEBUG,"This is a debug level information.");
        logHandler.logMessage(AbstractLogHandler.ERROR,"This is an error information.");
    }

}
