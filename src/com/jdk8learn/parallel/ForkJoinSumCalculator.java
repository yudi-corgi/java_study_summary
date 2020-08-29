package com.jdk8learn.parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 继承 RecursiveTask 类，通过"分支/合并"实现数值累积
 * @author YUDI
 * @date 2020/8/30 2:39
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    //数值累加的数组
    private final long[] numbers;
    private final int start;
    private final int end;

    //低于该阈值则任务不再划分出子任务
    private static final long THRESHOLD = 10000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers,0,numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if(length <= THRESHOLD){
            // 小于阈值则顺序累加并返回
            return computeSequentially();
        }
        // 划分子任务，计算数组的一半
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers,start,start + length/2);
        // 使用 forkJoinPool 线程异步执行新建的子任务
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start + length/2,end);
        // 同步执行第二个子任务，递归调用，若划分条件符合，会再划分出子任务
        Long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，未完成则等待
        Long leftResult = leftTask.join();
        // 两个子任务的结果组合
        return rightResult + leftResult;
    }

    // 子任务不可再划分时则累加数值
    private long computeSequentially(){
        long sum = 0;
        for(int i=start;i<end;i++){
            sum += numbers[i];
        }
        return sum;
    }

    // ForkJoinPool 调用方法
    public static long forJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

}
