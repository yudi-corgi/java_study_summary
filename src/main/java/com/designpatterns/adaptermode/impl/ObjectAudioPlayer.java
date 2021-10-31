package com.designpatterns.adaptermode.impl;

import com.designpatterns.adaptermode.adapter.ObjectMediaAdapter;
import com.designpatterns.adaptermode.MediaPlay;

/**
 * Target 目标实现类，用户调用
 * @author YUDI
 * @date 2020/8/15 15:56
 */
public class ObjectAudioPlayer implements MediaPlay {

    ObjectMediaAdapter oma ;

    @Override
    public void play(String audioType) {
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("内置了 .mp3 音频接口.");
        }else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("avi")){
            oma = new ObjectMediaAdapter(audioType);
            oma.play(audioType);
        }else{
            System.out.println("不支持的媒体文件格式.");
        }
    }
}
