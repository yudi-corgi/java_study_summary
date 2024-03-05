package com.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 控制线程顺序示例
 */
public class ThreadSequenceControlDemo {

    static AtomicInteger num = new AtomicInteger(1);

    public static void main(String[] args) {
        // ========================== 固定顺序

        // 1. 信号量控制线程顺序执行
        // Thread thread1 = new Thread(() -> SignalChangeOrdering.foo(1));
        // Thread thread2 = new Thread(() -> SignalChangeOrdering.foo(2));
        // Thread thread3 = new Thread(() -> SignalChangeOrdering.foo(3));
        // thread1.start();
        // thread2.start();
        // thread3.start();

        // 2. Lock + Condition 实现
        // LockConditionOrdering example = new LockConditionOrdering();
        // Thread t1 = new Thread(() -> example.foo(1));
        // Thread t2 = new Thread(() -> example.foo(2));
        // Thread t3 = new Thread(() -> example.foo(3));
        // t3.start();
        // t2.start();
        // t1.start();


        // ========================== 交替执行
        // 1. 两个线程交替打印奇偶数
        // new Thread(new WaitNotifyPrint.TurningRunner(), "偶数").start();
        // new Thread(new WaitNotifyPrint.TurningRunner(), "奇数").start();

        // 2. 三个线程交替打印 1-100
        LockSupportPrint print1 = new LockSupportPrint();
        LockSupportPrint print2 = new LockSupportPrint();
        LockSupportPrint print3 = new LockSupportPrint();
        Thread t1 = new Thread(print1::foo);
        t1.setName("线程一：");
        Thread t2 = new Thread(print2::foo);
        t2.setName("线程二：");
        Thread t3 = new Thread(print3::foo);
        t3.setName("线程三：");
        print1.setT(t2);
        print2.setT(t3);
        print3.setT(t1);
        t1.start();
        t2.start();
        t3.start();
        LockSupport.unpark(t1);
    }

}

class SignalChangeOrdering {
    // 信号量，volatile 控制变量在多线程场景的可见性
    static volatile int ticket = 1;
    // 线程休眠时间
    public final static int SLEEP_TIME = 1;

    public static void foo(int seq) {
        while (true) {
            if (ticket == seq) {
                try {
                    TimeUnit.MICROSECONDS.sleep(SLEEP_TIME);
                    // 每个线程循环打印3次
                    for (int i = 0; i < 3; i++) {
                        System.out.println(seq + " " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 信号量变更
                ticket = seq % 3 + 1;
                return;
            }
        }
    }
}

class LockConditionOrdering {

    // 信号量
    AtomicInteger ticket = new AtomicInteger(1);
    public Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();
    private final Condition[] conditions = {condition1, condition2, condition3};

    public void foo(int seq) {
        try {
            lock.lock();
            // 因为线程的执行顺序是不可预期的，因此需要每个线程自旋
            System.out.println("线程" + seq + " 开始执行");
            if(ticket.get() != seq) {
                try {
                    System.out.println("当前标识位为 " + ticket.get() + ",线程 " + seq + " 开始等待");
                    // 开始等待被唤醒
                    conditions[seq - 1].await();
                    System.out.println("线程 " + seq + " 被唤醒");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(seq);
            ticket.getAndIncrement();
            if (ticket.get() > 3) {
                ticket.set(1);
            }
            // 执行完毕，唤醒下一个线程，1>2>3
            conditions[seq % 3].signal();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}

class WaitNotifyPrint {
    private static int count = 0;
    //两个线程竞争该对象锁
    private static final Object lock = new Object();

    // 1. 拿到锁直接就打印。
    // 2. 打印完，唤醒其他线程，自己休眠
    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    // 拿到锁就打印
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    // 打印完，唤醒其他线程
                    lock.notify();
                    // 如果任务还没结束，就调用wait()让出当前的锁
                    if (count <= 100) {
                        try {
                            //自己休眠
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

class LockSupportPrint {

    private volatile Thread t;

    public void setT(Thread t) {
        this.t = t;
    }

    public void foo() {
        while (true) {
            // 进入之后立即阻塞
            LockSupport.park();
            if (ThreadSequenceControlDemo.num.get() > 100) {
                LockSupport.unpark(t);
                return;
            }
            System.out.println(Thread.currentThread().getName() + " : " + ThreadSequenceControlDemo.num.getAndIncrement());
            // 唤醒下一个线程
            LockSupport.unpark(t);
        }
    }

}