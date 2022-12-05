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

    @Override
    protected Class<?> findClass(String name) {

        String className = classPath + name + CLASS_SUFFIX;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(className));
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int len;
            byte[] data = new byte[1024];
            if ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            byte[] classBytes = baos.toByteArray();
            return defineClass(null, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Ending...");
        }
        return null;
    }

}
