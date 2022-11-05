package com.designpatterns.commandmode;

/**
 * @author YUDI-Corgi
 * @description 具体命令类：购买
 */
public class BuyCommand implements ICommand {

    private final Stock stock;

    public BuyCommand(Stock stock){
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.buy();
    }

}
