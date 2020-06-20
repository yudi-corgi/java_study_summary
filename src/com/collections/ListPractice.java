package com.collections;

import java.util.*;

/**
 * List 集合实践
 * @author YUDI
 */
public class ListPractice {

    public static void ads(MapPractice mapPractice){}


    public static void main(String[] args) {
        //1. 声明 String 类型的 List 集合
        List<String> list = new ArrayList<>();

        //添加元素
        list.add("这是List第一个元素!");
        list.add("OH YEAH!");
        list.add("一袋米要抗一楼!");

        //获取元素，下标从0开始，获取超出下标会报异常
        System.out.println("List 集合的容量：" + list.size());
        System.out.println("List 集合的第一个元素内容：" + list.get(0));

        //删除第一个元素，则第二个元素会变成第一个
        list.remove(0);
        System.out.println("List 集合删除元素后的第一个元素内容：" + list.get(0));

        /**
         * 一次添加多个元素
         * asList 是将数组转换为 List
         * 注意：该 List 是 java.util.Arrays 下的 ArrayList，并非 java.util 下的 ArrayList
         * 所以不包含 List 接口的元素修改等方法，如 add ,remove...
         */
        String[] ele = {"纳尼哦！","哦尼纳！"};
        List<String> list2 = Arrays.asList(ele);
        System.out.println("数组转换为List集合后的第一个元素："+list2.get(0));
        list.addAll(list2);

        //新增元素，该 add 操作会抛出异常
        //list2.add("123");

        //遍历集合元素一：for 循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println("List 第"+(i+1) + "个元素："+list.get(i));
        }

        //修改元素，参数一：要修改元素的下标  参数二：修改内容
        list.set(1,"一袋米要抗二楼！");

        //遍历集合元素二：iterator 迭代器
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println("迭代器遍历 List 元素：" + iterator.next());
        }

        //判断 List 中是否包含某个元素
        if(list.contains("哦尼纳！")){
            System.out.println("List 集合中包含元素：哦尼纳！");
        }

        //返回 List 集合中某个元素的索引
        if(list.contains("哦尼纳！")){
            //存在多个相同元素时，只返回第一个元素的索引
            System.out.println("List 集合中元素：哦尼纳！的索引为："+list.indexOf("哦尼纳！"));

            //返回最后一个元素的索引
            list.add("哦尼纳！");
            System.out.println("List 集合中元素：哦尼纳！最后一个的索引为："+ list.lastIndexOf("哦尼纳！"));
        }else{
            System.out.println("List 集合不包含元素：哦尼纳！");
        }

        //2. 声明一个 LinkedList
        List<String> linkedList = new LinkedList<>();
        linkedList.add("这是一个双向链表集合！");
        linkedList.add("包含前节点信息、数据元素、后节点信息！");
        linkedList.add("操作方式与 ArrayList 相似");
        System.out.println("linkedList 第一个元素："+linkedList.get(0));


        //3. 声明一个 String 类型的 Queue 队列
        Queue<String> queue = new LinkedList<>();
        queue.add("aa");
        queue.add("bb");
        queue.add("cc");

        //检索队列头元素且不删除
        System.out.println(queue.element());
        //检索队列头元素且删除
        System.out.println(queue.poll());
        //重新输出即可看到队列头元素变成 bb
        System.out.println(queue.element());
    }




}
