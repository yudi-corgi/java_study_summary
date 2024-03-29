package com.datastruct.lrulfu;

import java.util.HashMap;

/**
 * @author YUDI-Corgi
 * @description LRU 链表实现，手动构造链表与哈希表结合
 */
public class LruCache {

    /**
     * key -> Node(key, val)
     */
    private final HashMap<Integer, Node> map;
    /**
     * Node(k1, v1) <-> Node(k2, v2)...
     */
    private final DoubleList cache;
    /**
     * 记录 LRU 缓存的最大容量
     */
    int cap;


    public LruCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    /**
     * 查询 key，并晋升为最近使用
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).val;
    }

    /**
     * 添加 key 与 Node
     */
    public void put(int key, int val) {
        if (map.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key, val);
            return;
        }

        if (cap == cache.size()) {
            // 删除最久未使用的元素
            removeLeastRecently();
        }
        // 添加为最近使用的元素
        addRecently(key, val);
    }

    /**
     * 将某个 key 提升为最近使用
     */
    private void makeRecently(int key) {
        Node x = map.get(key);
        // 先从链表中删除这个节点
        cache.remove(x);
        // 重新插到队尾
        cache.addLast(x);
    }

    /**
     * 添加最近使用的元素
     */
    private void addRecently(int key, int val) {
        Node x = new Node(key, val);
        // 链表尾部就是最近使用的元素
        cache.addLast(x);
        // 在 map 中添加 key 的映射
        map.put(key, x);
    }

    /**
     * 删除某一个 key
     */
    private void deleteKey(int key) {
        Node x = map.get(key);
        // 从链表中删除
        cache.remove(x);
        // 从 map 中删除
        map.remove(key);
    }

    /**
     * 删除最久未使用的元素
     */
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node deletedNode = cache.removeFirst();
        // 从 map 中删除对应 key
        int deletedKey = deletedNode.key;
        map.remove(deletedKey);
    }

}

/**
 * 链表节点
 */
class Node {

    int key ,val;

    Node next, prev;

    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}

/**
 * 双向链表，此处双向链表只支持尾部插入，即靠尾部的数据是最近使用的，靠头部的数据是最久未使用的
 */
class DoubleList {

    private final Node head, tail;

    private int size;

    public DoubleList() {
        // 初始化双向链表的数据
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    /**
     * 在链表尾部添加节点 x，时间 O(1)
     */
    public void addLast(Node x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    /**
     * 删除链表中的 x 节点（x 一定存在）
     * 由于是双链表且给的是目标 Node 节点，时间 O(1)
     */
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    /**
     * 删除链表中第一个节点，并返回该节点，时间 O(1)
     */
    public Node removeFirst() {
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    /**
     * 返回链表长度，时间 O(1)
     */
    public int size() { return size; }

}
