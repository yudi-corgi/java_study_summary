package com.datastruct.linkedlist;

import java.util.Random;

/**
 * 跳表（以有序链表为基础）
 * @author YUDI
 * @date 2020/8/29 0:43
 */
public class SkipList {

    //节点晋升的概率
    private static final double PROMOTE_RATE = 0.5;
    private Node head,tail;
    private int maxLevel;

    public SkipList(){
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }

    /**
     * 内部节点类，包含上下左右地址的节点信息
     */
    public static class Node{
        public int data;
        public Node up,down,left,right;

        public Node(int data){
            this.data = data;
        }
    }

    // 查找节点
    public Node search(int data){
        Node node = findNode(data);
        if(node.data == data){
            System.out.println("找到节点："+ data);
            return node;
        }
        System.out.println("未找到节点："+ data);
        return null;
    }

    // 找到 data 对应的底层前置节点
    public Node findNode(int data){
        Node node = head;
        while (true){
            while (node.right.data != Integer.MAX_VALUE && node.right.data <= data){
                node = node.right;
            }
            if(node.down == null){
                break;
            }
            node = node.down;
        }
        return node;
    }

    // 插入节点
    public void insert(int data){
        Node preNode = findNode(data);
        // 若相同，直接返回
        if(preNode.data == data){
            return;
        }
        Node node = new Node(data);
        appendNode(preNode,node);
        int currentLevel = 0;
        //随机决定节点是否晋升
        Random random = new Random();
        while (random.nextDouble() < PROMOTE_RATE){
            // 如果当前层是最高层，则添加一层
            if(currentLevel == maxLevel){
                addLevel();
            }
            // 找到上一层的前置节点
            while(preNode.up == null){
                preNode = preNode.left;
            }
            preNode = preNode.up;
            // 把晋升的新节点插入到上一层
            Node upperNode = new Node(data);
            appendNode(preNode,upperNode);
            upperNode.down = node;
            node.up = upperNode;
            node = upperNode;
            currentLevel++;
        }
    }

    // 在前置节点后添加新节点
    public void appendNode(Node preNode,Node newNode){
        newNode.right = preNode.right;
        newNode.left = preNode;
        preNode.right.left = newNode;
        preNode.right = newNode;
    }

    //增加一层索引链表
    public void addLevel(){
        maxLevel++;
        Node p1 = new Node(Integer.MIN_VALUE);
        Node p2 = new Node(Integer.MAX_VALUE);
        p1.right = p2;
        p1.down = head;
        head.up = p1;
        p2.left = p1;
        p2.down = tail;
        tail.up = p2;
        head = p1;
        tail = p2;
    }

    // 节点删除
    public boolean remove(int data){
        Node node = search(data);
        if(node == null){
            return false;
        }
        int currentLevel = 0;
        while(node != null){
            node.left.right = node.right;
            node.right.left = node.left;
            //如果不是最底层，且只有无穷小和无穷大节点，删除该层
            if(currentLevel != 0 && node.left.data == Integer.MIN_VALUE && node.right.data == Integer.MAX_VALUE){
                removeLevel(node.left);
            }else{
                currentLevel++;
            }
            node = node.up;
        }
        return true;
    }

    // 删除一层索引链表
    public void removeLevel(Node node){
        Node right = node.right;
        // 如果是最高层
        if(node.up == null){
            node.down.up = null;
            right.down.up = null;
        }else{
            node.up.down = node.down;
            node.down.up = node.up;
            right.up.down = right.down;
            right.down.up = right.up;
        }
        maxLevel--;
    }

    // 输出底层链表
    public void printList(){
        Node node = head;
        while(node.down != null){
            node = node.down;
        }
        while(node.right.data != Integer.MAX_VALUE){
            System.out.print(node.right.data + " ");
            node = node.right;
        }
        System.out.println();
    }


}
