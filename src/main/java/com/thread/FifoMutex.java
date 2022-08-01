package com.thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author YUDI-Corgi
 * @description LockSupport 实现的先进先出锁，队列首线程才能获取锁
 */
public class FifoMutex {

    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    /**
     * 获取锁
     */
    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);

        // 只有队首的线程才能获取锁
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            // 若当前线程不是队首或者锁已被其它线程获取则挂起自己
            LockSupport.park(this);
            // 若线程是因为中断才被唤醒，则重置中断状态，并设置标识
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }
        }

        // 从队列中移除队首线程（表示线程已获取到锁）
        waiters.remove();
        // 若线程被中断过，则在此处直接撞断它
        if (wasInterrupted) {
            current.interrupt();
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }


}
