package com.designpatterns.visitormode;

/**
 * 元素接口：账单
 * @author CDY
 * @date 2020/9/10
 */
public interface Bill {

    /**
     * 接受一个访问者
     * @param visitor 访问者
     */
    void accept(Visitor visitor);

}
