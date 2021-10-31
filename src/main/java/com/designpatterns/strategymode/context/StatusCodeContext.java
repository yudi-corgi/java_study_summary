package com.designpatterns.strategymode.context;

import com.designpatterns.strategymode.StatusCodeStrategy;
import com.designpatterns.strategymode.enums.StatusCodeEnum;
import com.designpatterns.strategymode.impl.DefaultProcess;

import java.util.HashMap;
import java.util.Map;

/**
 * 状态码环境类
 * @author YUDI
 * @date 2020/8/15 17:52
 */
public class StatusCodeContext {

    public static StatusCodeStrategy getInstance(int code){
        Map<Integer,String> allClazz = StatusCodeEnum.getAllClazz();
        String clazz = allClazz.get(code);
        StatusCodeStrategy scs = new DefaultProcess();
        try {
            if(clazz != null){
                scs = (StatusCodeStrategy) Class.forName(clazz).newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scs;
    }
}


