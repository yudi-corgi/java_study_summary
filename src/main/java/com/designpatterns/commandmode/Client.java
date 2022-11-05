package com.designpatterns.commandmode;

/**
 * @author YUDI-Corgi
 * @description 客户端
 */
public class Client {

    public static void main(String[] args) {

        Stock stock = new Stock();

        BuyCommand buyCommand = new BuyCommand(stock);
        SellCommand sellCommand = new SellCommand(stock);

        // 调用者调用操作
        Invoker invoker = new Invoker();
        invoker.takeCommand(buyCommand);
        invoker.takeCommand(sellCommand);
        invoker.placeCommand();

    }

}
