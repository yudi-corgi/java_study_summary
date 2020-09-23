package com.datastruct.linkedlist;

/**
 * 链表类
 * @author YUDI
 * @date 2020/7/28 19:46
 */
public class Node {

    String msg;
    Node next;

    @Override
    public String toString() {
        return "Node{" +
                "msg='" + msg + '\'' +
                ", next=" + next +
                '}';
    }
}
