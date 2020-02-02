package com.collections;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author YUDI
 * @date 2020/2/2 14:30
 */
public class MapPractice {

    public static void main(String[] args) {

        //1. 声明 K 为 String，V 为 Object 的 Map 集合
        Map<String,Object> map = new HashMap<>();

        //新增元素
        map.put("1","字符串元素");
        map.put("2",1);
        map.put("3",'c');

        //遍历集合一，获取所有 key 的 Set 集合
        Set<String> mapKey = map.keySet();
        for (String k : mapKey) {
            System.out.println("获取所有 Key 值遍历：" + map.get(k));
        }

        //修改元素
        map.put("3","字符 c 修改为字符串");

        //遍历集合二，获取 map 的 Entry 对象
        Set<Map.Entry<String,Object>> entrySet = map.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            System.out.print("map 集合的 key 值：" + entry.getKey() + ", ");
            System.out.println("value 值：" + entry.getValue());
        }

        //删除元素
        map.remove("3");
        //重新遍历
        for (Map.Entry<String, Object> entry : entrySet) {
            System.out.print("map 集合的 key 值：" + entry.getKey() + ", ");
            System.out.println("value 值：" + entry.getValue());
        }

        //判断 Map 集合中是否包含某个 Key 值或 Value 值
        if(map.containsKey("1")){
            System.out.println("map 集合包含值为：1 的 Key");
        }else{
            System.out.println("map 集合不包含值为：1 的 Key");
        }

        if(map.containsValue("字符串元素")){
            System.out.println("map 集合不包含值为：字符串元素 的 Value");
        }else{
            System.out.println("map 集合包含值为：字符串元素 的 Value");
        }

        //2. 声明 K 为 String，V 为 Object 的 Hashtable 集合
        Hashtable<String,Object> table = new Hashtable<>();
        table.put("1","不允许存在 null 的 Key");
        table.put("2","线程安全");
        table.put("3","继承自 Dictionary 类，实现了 Map 接口");
        System.out.println("Hashtable 是否包含 Key 值为 1 的元素：" + table.containsKey("1"));

    }
}
