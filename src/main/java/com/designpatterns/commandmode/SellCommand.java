package com.designpatterns.commandmode;

/**
 * @author YUDI-Corgi
 * @description 具体命令类：出售
 */
public class SellCommand implements ICommand{

    private final Stock stock;

    public SellCommand(Stock stock){
        this.stock = stock;
    }

    @Override
    public void execute() {
        stock.sell();
    }
}
