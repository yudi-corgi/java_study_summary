package com.datastruct.lrulfu;

import java.util.LinkedHashMap;

/**
 * @author YUDI-Corgi
 * @description LRU 链表实现，基于 LinkedHashMap
 */
public class LruCacheWithLinkedHashMap {

    /**
     * 最大容量
     */
    int cap;

    /**
     * K-V 缓存哈希表
     */
    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

    public LruCacheWithLinkedHashMap(int capacity) {
        this.cap = capacity;
    }

    /**
     * 查询
     */
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    /**
     * 新增
     */
    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            // 修改 key 的值
            cache.put(key, val);
            // 将 key 变为最近使用
            makeRecently(key);
            return;
        }

        if (cache.size() >= this.cap) {
            // 链表头部就是最久未使用的 key
            int oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
        // 将新的 key 添加链表尾部
        cache.put(key, val);
    }

    /**
     * key 晋升为最近使用
     */
    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }

}
