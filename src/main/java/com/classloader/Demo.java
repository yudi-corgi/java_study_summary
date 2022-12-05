package com.classloader;

import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author YUDI-Corgi
 * @description 测试
 */
public class Demo {

    @SneakyThrows
    public static void main(String[] args) {
        // 循环创建新的自定义类加载器加载类，当类的内容修改后重新加载即可实现代码热替换
        while (true) {
            UserClassLoader userClassLoader = new UserClassLoader(null, "d:\\");
            Class<?> easyClass = userClassLoader.loadClass("EasyClass");
            System.out.println(easyClass.getClassLoader().getClass().getName());
            System.out.println(easyClass.getClassLoader().getParent());
            Object o = easyClass.getDeclaredConstructor().newInstance();
            Method old = easyClass.getMethod("old");
            old.invoke(o);
            TimeUnit.SECONDS.sleep(30);
        }
    }

}
