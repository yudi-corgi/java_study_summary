package com.designpatterns.strategymode.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举类，用来保存所有具体状态策略类的全限定名
 * @author YUDI
 * @date 2020/8/15 17:49
 */
public enum StatusCodeEnum {

    REQUEST(200,"com.designpatterns.strategymode.impl.RequestSuccess"),
    SERVER(500,"com.designpatterns.strategymode.impl.ServerError");
    private static final Map<Integer, String> allClazz = new HashMap<>();

    // 静态代码，将枚举信息都添加到 hashMap
    static{
        for (StatusCodeEnum sc : StatusCodeEnum.values()) {
            allClazz.put(sc.getCode(),sc.getClazz());
        }
    }

    // 返回 allClzz
    public static Map<Integer,String> getAllClazz(){
        return allClazz;
    }

    private int code;
    private String clazz;

    StatusCodeEnum(int code, String clazz) {
        this.code = code;
        this.clazz = clazz;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
