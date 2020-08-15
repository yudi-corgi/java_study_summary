package com.designpatterns.adaptermode;

import com.designpatterns.adaptermode.impl.ClassAudioPlayer;
import com.designpatterns.adaptermode.impl.ObjectAudioPlayer;

/**
 * 适配器测试
 * @author YUDI
 * @date 2020/8/15 16:14
 */
public class AllTest {

    public static void main(String[] args) {
        System.out.println("类适配器：");
        ClassAudioPlayer cap = new ClassAudioPlayer();
        cap.play("vlc");

        System.out.println("对象适配器：");
        ObjectAudioPlayer oap = new ObjectAudioPlayer();
        oap.play("avi");
    }

}
