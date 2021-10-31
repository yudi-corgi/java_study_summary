package com.designpatterns.adaptermode.adapter;

import com.designpatterns.adaptermode.impl.AviPlayer;
import com.designpatterns.adaptermode.impl.VlcPlayer;
import com.designpatterns.adaptermode.AdvanceMediaPlay;
import com.designpatterns.adaptermode.MediaPlay;

/**
 * 对象适配器
 * @author YUDI
 * @date 2020/8/15 15:58
 */
public class ObjectMediaAdapter implements MediaPlay {

    AdvanceMediaPlay advanceMediaPlay;

    public ObjectMediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")){
            advanceMediaPlay = new VlcPlayer();
        }else if (audioType.equalsIgnoreCase("avi")){
            advanceMediaPlay = new AviPlayer();
        }
    }

    @Override
    public void play(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")){
            System.out.println("调用 .vlc 视频接口.");
            advanceMediaPlay.playVlc();
        }else if (audioType.equalsIgnoreCase("avi")){
            System.out.println("调用 .avi 视频接口.");
            advanceMediaPlay.playAvi();
        }
        System.out.println("对象适配器采用组合方式.");
        System.out.println("比起单继承，能适配视频接口的所有实现类.");
    }
}
