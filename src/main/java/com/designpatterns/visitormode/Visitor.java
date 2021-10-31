package com.designpatterns.visitormode;

/**
 * 接口访问者
 * @author CDY
 * @date 2020/9/10
 */
public interface Visitor {

    /**
     * 针对抽象访问者
     * @param bill 账单
     */
    void visitAbstractVisitor(AbstractBill bill);

}
