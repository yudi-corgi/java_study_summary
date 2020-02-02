package com.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author YUDI
 * @date 2020/2/2 14:30
 */
public class SetPractice {

    public static void main(String[] args) {

        // 声明 String 类型的 Set 集合
        Set<String> set = new HashSet<>();

        //新增元素
        set.add("这是一个Set集合!");
        set.add("特性：无序，不可重复!");
        set.add("因为无序，所以没有下标，不能for(;;)方式循环!");
        set.add("OMG!");

        System.out.println("Set 集合个数："+set.size());

        // 遍历集合一：forEach 增强循环
        for (String s : set) {
            System.out.println("Set 集合ForEach遍历：" + s);
        }

        //删除元素
        set.remove("OMG!");

        // 遍历集合二：迭代器
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println("Set 集合迭代器遍历："+iterator.next());
        }

        //判断 Set 集合是否包含某个元素
        if(set.contains("OMG!")){
            System.out.println("Set 集合包含元素：OMG!");
        }else{
            System.out.println("Set 集合不包含元素：OMG!");
        }

    }

}
