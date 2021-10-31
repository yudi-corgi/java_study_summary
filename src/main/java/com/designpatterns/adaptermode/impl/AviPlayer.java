package com.designpatterns.adaptermode.impl;

import com.designpatterns.adaptermode.AdvanceMediaPlay;

/**
 * Adaptee，被适配者接口实现类
 * @author YUDI
 * @date 2020/8/15 15:57
 */
public class AviPlayer implements AdvanceMediaPlay {

    @Override
    public void playVlc() {

    }

    @Override
    public void playAvi() {
        System.out.println("播放 .avi 视频.");
    }
}
