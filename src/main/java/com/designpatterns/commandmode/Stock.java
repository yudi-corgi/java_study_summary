package com.designpatterns.commandmode;

/**
 * @author YUDI-Corgi
 * @description 模拟接收者执行命令
 */
public class Stock {

    private final String name = "ABC";
    private final int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: " + name + ", Quantity: " + quantity + " ] sold");
    }

}
