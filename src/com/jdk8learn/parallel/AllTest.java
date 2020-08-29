package com.jdk8learn.parallel;

import com.jdk8learn.streamLearn.PracticeLambda2;

/**
 * 并行功能测试类
 * @author YUDI
 * @date 2020/8/30 2:57
 */
public class AllTest {

    public static void main(String[] args) {
        long faster = Long.MAX_VALUE;
        for(int i=0;i<10;i++){
            long start = System.nanoTime();
            ForkJoinSumCalculator.forJoinSum(10000000);
            long duration = (System.nanoTime() - start) / 1000000;
            if(duration < faster){
                faster = duration;
            }
        }
        System.out.println("自定义分支/合并累加数值花费时间:"+faster);
    }

}
