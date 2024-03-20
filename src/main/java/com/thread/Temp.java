package com.thread;


import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author YUDI-Corgi
 * @description
 */
public class Temp {

    private String a;

    static final int MAX_COUNT = 1000 * 10000;
    static final String[] arr = new String[MAX_COUNT];


    public static void main(String[] args) throws InterruptedException {

        System.out.println(new Date(1545218064000L));

        // PS：JDK 11 没有 com.msic.Version 这个类了
        // String name = "Java"; // 这一句注释掉，下面就是 true（某一天回来看，希望你还记得原因是什么，提示：首次出现）
        // StringBuilder sb = new StringBuilder("Ja");
        // sb.append("va");
        // String s = sb.toString();
        // String a = "java";
        // System.out.println(sb.intern() == sb); // false
        // System.out.println(a.intern() == sb.intern());

        // String b = "xl1";
        // String c = "cl2";
        // String d = b + c;
        // d.intern();
        // String e = "xl1cl2";
        // String f = e + "1";
        // System.out.println(e == d);



        // String s2 = "xl1cl2";
        // String s = new String("xl1cl2");
        // String intern = s.intern();
        // System.out.println(s == s2);
        // System.out.println(intern == s2);


        Integer[] data = {1,2,3,4,5,6,7,8,9,10};
        long start = System.currentTimeMillis();

        for (int i = 0; i < MAX_COUNT; i++) {
            arr[i] = new String(String.valueOf(data[i % data.length]));
            // arr[i] = new String(String.valueOf(data[i % data.length])).intern();
        }

        System.out.println("消耗时间："+(System.currentTimeMillis() - start));

        Thread.sleep(10000000);
        System.gc();

    }

}
