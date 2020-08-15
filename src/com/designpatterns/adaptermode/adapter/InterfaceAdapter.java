package com.designpatterns.adaptermode.adapter;

import com.designpatterns.adaptermode.impl.SuperBigInterface;

/**
 * 接口适配器，为接口提供一个缺省实现
 * 其它类继承该类进行扩展，实现需要的方法即可，而不是如下面代码实现所有方法
 * 也可以在这里为某些方法提供默认实现，然后根据业务决定子类是否需要重写方法
 * @author YUDI
 * @date 2020/8/15 16:08
 */
public class InterfaceAdapter implements SuperBigInterface {

    @Override
    public void one() {

    }

    @Override
    public void two() {

    }

    @Override
    public void three() {

    }

    @Override
    public void four() {

    }

    @Override
    public void five() {

    }

    @Override
    public void six() {

    }

    @Override
    public void seven() {

    }
}
