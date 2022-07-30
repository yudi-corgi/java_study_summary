package com.thread;


import java.util.concurrent.*;

/**
 * 线程池（ThreadPool）简单使用
 * @author YUDI
 * @date 2020/5/18 10:41
 */
public class ThreadPoolPractice {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(5);
        CallableImpl c = new CallableImpl();
        Future<String> f = es.submit(c);
        System.out.println(f.get() + "<<");

        //构建线程池
        // 1. 创建数组阻塞队列
        ArrayBlockingQueue<Runnable> array = new ArrayBlockingQueue<>(10);
        // 2. 创建任务拒绝策略
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
        // 3. 创建线程池，核心池数量：10，最大线程数量：20，存活时间：10 秒
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(10,20,10, TimeUnit.SECONDS,array,handler);
        // 4. 启用 15 个任务线程
        for(int j=0;j<15;j++){
            // 输出返回是 NULL， get() 会阻塞直到任务执行完返回结果
            System.out.println(tpe.submit(new RunnableImpl()).get());
        }

        // 通过 CountDownLatch 设置等待执行完成的任务数量
        CountDownLatch latch = new CountDownLatch(1);
        tpe.submit(() -> {
            try{
                System.out.println("任务执行");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 计数器等待执行完成的任务数量 -1
                latch.countDown();
            }
        });
        // 当前线程等待，直到计数器等待执行完成的任务数量变为 0，才会接着执行
        latch.await();
        // 直接指定等待时间
        // latch.await(10,TimeUnit.SECONDS);

        // 提交线程池
        Future<CallableImpl> tpeFuture = tpe.submit(CallableImpl::new);
        System.out.println("线程池调用 Callable 接口创建的线程后返回结果(返回结果是类对象)："+tpeFuture.get());
        // 自建
        FutureTask<String> future = new FutureTask<>(new CallableImpl());
        new Thread(future,"有返回值的线程").start();
        System.out.println("自建调用 Callable 接口创建的线程后返回结果(返回结果是实际值)："+future.get());

        /*new FutureTask<>(() ->
            {
                System.out.println("输出");
                return "自调用";
            }
        );*/

        System.out.println("完成的任务数量："+tpe.getCompletedTaskCount());
        // 线程池停止，不接收新任务，但会执行完阻塞队列的任务
        // tpe.shutdown();
        // 线程池停止，不接收新任务，不执行阻塞队列的任务，中断正在执行的任务线程，返回尚未执行完成的线程集合
        // tpe.shutdownNow();
    }

}
