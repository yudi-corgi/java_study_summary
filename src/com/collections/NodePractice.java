package com.collections;

/**
 * 链表相关操作测试类
 * @author YUDI
 * @date 2020/7/28 19:47
 */
public class NodePractice {

    public static Node createNode(String[] arr){

        Node n = new Node();
        Node h = new Node();
        for (int i = 0; i < arr.length; i++) {
            if(i==0){
                h = n;
            }
            n.msg = arr[i];
            Node tmp = n;
            if(i != (arr.length-1)){
                n = new Node();
                tmp.next = n;
            }
        }
        return h;
    }

    // 翻转链表 -- 迭代方式
    public static Node reverseNodeByIteration(Node n){

        if(n == null || n.next == null){
            return n;
        }
        Node p = n,newH = null;
        while(p != null){
            Node tmp = p.next;
            p.next = newH;
            newH = p;
            p = tmp;
        }
        return newH;
    }

    // 翻转链表 -- 递归方式
    public static Node reverseNodeByRecursion(Node n){
        if(n == null || n.next == null){
            return n;
        }
        Node newHead = reverseNodeByRecursion(n.next);
        n.next.next = n;
        n.next = null;
        return newHead;
    }


    public static void main(String[] args) {
        System.out.println("===============================链表翻转=============================");
        String[] arr = {"abc", "123", "呵呵", "测试"};
        Node n = NodePractice.createNode(arr);
        System.out.println("链表输出："+n);
        Node n1 = reverseNodeByIteration(n);
        System.out.println("迭代方式："+n1);
        Node n2 = reverseNodeByRecursion(n1);
        System.out.println("递归方式："+n2);
        System.out.println("===============================跳表测试=============================");
        SkipList list=new SkipList();
        list.insert(50);
        list.insert(15);
        list.insert(13);
        list.insert(20);
        list.insert(100);
        list.insert(75);
        list.insert(99);
        list.insert(76);
        list.insert(83);
        list.insert(65);
        list.printList();
        list.search(50);
        list.search(22);
        System.out.println(list.remove(50)?"节点：50 删除成功":"节点：50 删除失败");
        list.search(50);
    }
}
