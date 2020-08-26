package com.jdk8learn.streamLearn;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PracticeLambda2 {

    /**
     * 判断数值是否为质数
     * @param candidate 数值
     * @return true 是 false 否
     */
    public static boolean isPrime(int candidate){
        // 只判断小于等于参数的平方根的因子
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(n -> candidate % n == 0);
        // 全部判断
        // return IntStream.rangeClosed(2,candidate).noneMatch(n -> n % candidate == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,100)
                .boxed().collect(Collectors.partitioningBy(PracticeLambda2::isPrime));
    }

    public static void main(String[] args) {
        inter inter = (a,b)->a+b;
        System.out.println(inter.operation(3,2));
        System.out.println("Hello World!");

        // 根据传入数值n，将前 N 个数分为质数和非质数(包含 n)
        Map<Boolean, List<Integer>> primeMap = PracticeLambda2.partitionPrimes(100);
        System.out.println(primeMap);
    }
}
