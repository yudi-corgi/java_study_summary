package com.designpatterns.chainormode;

/**
 * @author YUDI
 * @date 2020/9/1 21:51
 */
public abstract class AbstractLogHandler {

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;
    protected int level;
    /** 责任链中的下一引用 */
    protected AbstractLogHandler successor;
    public void setSuccessor(AbstractLogHandler successor) {
        this.successor = successor;
    }
    public void logMessage(int level, String message){
        if(this.level <= level){
            write(message);
        }
        if (successor != null) {
            successor.logMessage(level,message);
        }
    }
    /** 输出日志 */
    protected abstract void write(String message);

}
