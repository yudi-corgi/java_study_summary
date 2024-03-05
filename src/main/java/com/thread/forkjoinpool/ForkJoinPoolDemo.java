package com.thread.forkjoinpool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool 线程池使用
 */
public class ForkJoinPoolDemo {

    public static int fibonacci(int n){
        if (n == 1 || n == 2) {
            return 1;
        }
        if (n > 2) {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
        return -1;
    }

    public static void main(String[] args) {

        long t = System.currentTimeMillis();
        System.out.println(ForkJoinPoolDemo.fibonacci(20));
        System.out.println(System.currentTimeMillis() - t);

        int n = 20;

        // 为了追踪子线程名称，需要重写 ForkJoinWorkerThreadFactory 的方法
        final ForkJoinPool.ForkJoinWorkerThreadFactory factory = pool -> {
            final ForkJoinWorkerThread worker = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            worker.setName("my-thread" + worker.getPoolIndex());
            return worker;
        };

        // 创建分治任务线程池，可以追踪到线程名称
        ForkJoinPool forkJoinPool = new ForkJoinPool(4, factory, null, false);

        // 快速创建ForkJoinPool方法
        //ForkJoinPool forkJoinPool = new ForkJoinPool(4);

        // 创建分治任务
        Fibonacci fibonacci = new Fibonacci(n);

        // 调用invoke方法启动分治任务
        long now = System.currentTimeMillis();
        Integer result=forkJoinPool.invoke(fibonacci);
        System.out.println("Fibonacci:" + n + " 的结果是 - " + result);
        System.out.println("耗时：" + (System.currentTimeMillis() - now) + "ms");
        // 这里只是用法示例，ForkJoinTask 的 javadoc 也说了用 ForkJoinPool 来实现 Fibonacci 很 dumb ~.~
    }

}

/**
 * 定义拆分任务，写好拆分逻辑
 */
class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n){
            this.n = n;
        }

        @Override
        public Integer compute(){
            //和递归类似，定义可计算的最小单元
            if (n <= 1) {
                return n;
            }
            // System.out.println(Thread.currentThread().getName());

            Fibonacci f1 = new Fibonacci(n - 1);
            // 拆分成子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            // f1.join 等待子任务执行结果
            return f2.compute() + f1.join();
        }
}
