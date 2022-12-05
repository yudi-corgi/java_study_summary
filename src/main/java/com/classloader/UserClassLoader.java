package com.classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author YUDI-Corgi
 * @description 自定义类加载器
 */
public class UserClassLoader extends ClassLoader {

    /**
     * 加载的类路径
     */
    private final String classPath;

    /**
     * 类后缀
     */
    private static final String CLASS_SUFFIX = ".class";

    public UserClassLoader(ClassLoader parent, String classPath) {
        super(parent);
        this.classPath = classPath;
    }

    /**
     * 重写 ClassLoader 的 findClass 方法，不破坏其双亲委派模式
     * @param name 类名
     * @return 加载的 Class 对象
     */
    @Override
    protected Class<?> findClass(String name) {

        // 1. 读取 class 文件
        String className = classPath + name + CLASS_SUFFIX;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(className));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int len;
            byte[] data = new byte[1024];
            if ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            // 2. 转为字节数组
            byte[] classBytes = baos.toByteArray();
            // 3. 调用 ClassLoader#defineClass 将字节数组转为 Class 实例
            return defineClass(null, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Ending...");
        }
        return null;
    }

}
