package com.designpatterns.commandmode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YUDI-Corgi
 * @description 调用者：命令调用
 */
public class Invoker {

    private final List<ICommand> commands = new ArrayList<>();

    public void takeCommand(ICommand command){
        commands.add(command);
    }

    public void placeCommand(){
        for (ICommand command : commands) {
            command.execute();
        }
        commands.clear();
    }

}
