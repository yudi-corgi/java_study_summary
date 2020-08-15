package com.designpatterns.adaptermode;

import com.designpatterns.adaptermode.adapter.ClassMediaAdapter;
import com.designpatterns.adaptermode.impl.MediaPlay;

/**
 * @author YUDI
 * @date 2020/8/15 16:33
 */
public class ClassAudioPlayer implements MediaPlay {
    ClassMediaAdapter cma;
    @Override
    public void play(String audioType) {
        if(audioType.equalsIgnoreCase("mp3")){
            System.out.println("内置 .mp3 音频接口.");
        }else if(audioType.equalsIgnoreCase("vlc")){
            System.out.println("适配 .vlc 视频接口.");
            cma = new ClassMediaAdapter();
            cma.play(audioType);
        }else{
            System.out.println("不支持的媒体文件格式.");
        }
    }
}
