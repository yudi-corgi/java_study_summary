package com.datastruct.linkedlist;

/**
 * 链表相关操作类
 * @author YUDI
 * @date 2020/7/28 19:47
 */
public class NodePractice {

    /**
     * 翻转链表 -- 迭代方式
     * @param n 链表
     * @return 翻转后的链表
     */
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

    /**
     * 翻转链表 -- 递归方式
     * @param n 链表
     * @return 翻转后的链表
     */
    public static Node reverseNodeByRecursion(Node n){
        if(n == null || n.next == null){
            return n;
        }
        Node newHead = reverseNodeByRecursion(n.next);
        n.next.next = n;
        n.next = null;
        return newHead;
    }

    /**
     * 在链表中查找倒数第 n 个结点
     * @param head 链表
     * @param n 要查找的倒数第 n 个节点
     * @return 返回第 n 个节点
     */
    public static Node findNthFromEnd(Node head, int n) {
        Node n1 = head;
        Node n2 = head;
        for (int i = 1; i < n; i++) {
            n2 = n2.next;
            if(n2 == null){
                throw new IllegalArgumentException("参数 n 超出链表长度！");
            }
        }
        while(n2.next != null){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    public static void main(String[] args) {
        System.out.println("===============================链表翻转=============================");
        String[] arr = {"abc", "123", "呵呵", "测试"};
        Node n = Node.buildLinkedList(arr);
        System.out.println("链表输出："+n);
        Node reverse1 = reverseNodeByIteration(n);
        System.out.println("迭代方式：" + reverse1);
        Node reverse2 = reverseNodeByRecursion(reverse1);
        System.out.println("递归方式：" + reverse2);

        System.out.println("===============================跳表测试=============================");
        SkipList list = new SkipList();
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
        System.out.println(list.remove(50) ? "节点：50 删除成功" : "节点：50 删除失败");
        list.search(50);

        System.out.println("===============================查找倒数节点==========================");
        Integer[] inputs = {5,3,7,2,4,1,9,8};
        Node linkedList = Node.buildLinkedList(inputs);
        System.out.println("查找链表倒数第 3 个结点：" + findNthFromEnd(linkedList, 3).data);
        System.out.println("查找链表倒数第 5 个结点：" + findNthFromEnd(linkedList, 5).data);
    }
}
