package com.datastruct.linkedlist;

/**
 * 链表类
 * @author YUDI
 * @date 2020/7/28 19:46
 */
public class Node {

    Object data;
    Node next;

    public Node(){}

    public Node(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data='" + data + '\'' +
                ", next=" + next +
                '}';
    }

    /**
     * 通用方法：创建链表
     * @param array 数据数组
     * @return  链表
     */
    public static Node buildLinkedList(Object[] array){
        Node head = new Node(array[0]);
        Node p = head;
        for (int i = 1; i < array.length; i++) {
            p.next = new Node(array[i]);
            p = p.next;
        }
        return head;
    }
}
