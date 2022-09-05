package com.datastruct.lrulfu;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * @author YUDI-Corgi
 * @description LFU 链表实现，基于 LinkedHashMap
 */
public class LfuCache {

    public static void main(String[] args) {
        LfuCache lfu = new LfuCache(4);
        lfu.put(1,1);
        lfu.put(2,2);
        lfu.put(3,3);
        lfu.put(4,4);

        lfu.get(1);
        lfu.get(1);
        lfu.get(2);
        lfu.get(3);
        lfu.get(3);
        lfu.get(4);
        lfu.get(4);
        lfu.get(4);

        lfu.put(5, 5);
        lfu.put(4, 44);
    }

    /**
     * key 到 val 的映射，下称 KV 表
     */
    HashMap<Integer, Integer> keyToVal;
    /**
     * key 到 freq 的映射，下称 KF 表
     */
    HashMap<Integer, Integer> keyToFreq;
    /**
     * freq 到 key 列表的映射，下称 FK 表
     */
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    /**
     * 记录最小的频次
     */
    int minFreq;
    /**
     * 记录 LFU 缓存的最大容量
     */
    int cap;

    /**
     * 构造容量为 capacity 的缓存
     */
    public LfuCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }

    /**
     * 在缓存中查询 key
     */
    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        // 增加 key 对应的 freq
        increaseFreq(key);
        return keyToVal.get(key);
    }

    /**
     * 将 key 和 val 存入缓存
     */
    public void put(int key, int val) {
        if (this.cap <= 0) {
            return;
        }

        // 若 key 已存在，修改对应的 val 即可
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, val);
            // key 对应的 freq 加一
            increaseFreq(key);
            return;
        }

        // key 不存在，需要插入

        if (this.cap <= keyToVal.size()) {
            // 容量已满的话需要淘汰一个 freq 最小的 key
            removeMinFreqKey();
        }

        // 插入 key 和 val，对应的 freq 为 1
        // 插入 KV 表
        keyToVal.put(key, val);
        // 插入 KF 表
        keyToFreq.put(key, 1);
        // 插入 FK 表
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        // 插入新 key 后最小的 freq 即为是 1
        this.minFreq = 1;
    }

    /**
     * 增加 freq
     * @param key Key
     */
    private void increaseFreq(int key) {
        // 更新 KF 表
        Integer freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);

        // 更新 FK 表
        // 原 freq 移除 key
        freqToKeys.get(freq).remove(key);

        // freq+1 增加 key
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);

        // 如果 freq 对应的列表空了，移除这个 freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            // 如果这个 freq 恰好是 minFreq，更新 minFreq
            if (freq == this.minFreq) {
                // 直接加 1 是因为上边刚好新增了 freq+1 嘛
                this.minFreq++;
            }
        }
    }

    /**
     * 移除 freq 最小（或 freq 最小中最旧）的 key
     */
    private void removeMinFreqKey() {

        // freq 最小的 key 列表
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);

        // 其中最先被插入的那个 key 就是该被淘汰的 key
        int deletedKey = keyList.iterator().next();
        // 移除 FK 表最新的 key
        keyList.remove(deletedKey);

        if (keyList.isEmpty()) {
            freqToKeys.remove(this.minFreq);
            // 问：这里需要更新 minFreq 的值吗？
            // 不需要，即使 freq 变化了，但当前方法的调用只在 put 方法中可能被调用
            // 那就说明肯定是有新值插入，那么 minFreq 肯定又会变化为 1，因此没必要修改
            // 若要修改，那么需要线性遍历 KF（FK） 表取到最小 freq，时间复杂度就要变为 O(n) 了
        }

        // 更新 KV 表
        keyToVal.remove(deletedKey);
        // 更新 KF 表
        keyToFreq.remove(deletedKey);
    }

}
