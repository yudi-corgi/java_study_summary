package com.designpatterns.adaptermode.adapter;

import com.designpatterns.adaptermode.VlcPlayer;
import com.designpatterns.adaptermode.impl.MediaPlay;

/**
 * 类适配器
 * @author YUDI
 * @date 2020/8/15 16:03
 */
public class ClassMediaAdapter extends VlcPlayer implements MediaPlay {

    @Override
    public void play(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")){
            super.playVlc();
            System.out.println("适配了视频播放接口");
            System.out.println("但由于是单继承，所以对于视频接口只能调用继承的父类方法。");
        }else{
            System.out.println("不支持 .vlc 之外的视频格式.");
        }
    }
}
