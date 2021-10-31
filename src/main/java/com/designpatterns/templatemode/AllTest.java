package com.designpatterns.templatemode;

import com.designpatterns.templatemode.impl.GearGame;
import com.designpatterns.templatemode.impl.NierGame;

/**
 * 模板模式测试
 * @author YUDI
 * @date 2020/8/15 22:50
 */
public class AllTest {

    public static void main(String[] args) {

        Game nier = new NierGame();
        nier.init();
        nier.doStart();
        nier.doEnd();

        Game gear = new GearGame();
        gear.init();
        gear.doStart();
        gear.doEnd();
    }


}
