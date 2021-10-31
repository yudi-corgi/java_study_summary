package com.jdk8learn.streamLearn;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 自定义收集器 实现质数、非质数的分区
 * @author YUDI
 * @date 2020/8/28 0:22
 */
public class PrimeNumbersCollector implements Collector<Integer,
        Map<Boolean, List<Integer>>,Map<Boolean, List<Integer>>> {

    /**
     * 截取小于待测数的质数列表
     * @param list 质数列表
     * @param p   判断函数
     * @param <A> Integer
     * @return 小于待测数的质数列表 List
     */
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for (A a : list) {
            if(!p.test(a)){
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }

    /**
     * 判断到测数是否为质数
     * @param primeList 已保存的质数列表
     * @param candidate 待测试
     * @return  true 质数 false 非质数
     */
    public static boolean isPrime(List<Integer> primeList, int candidate){
        int candidateRoot = (int) Math.sqrt(candidate);
        return takeWhile(primeList, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
    }

    /**
     * 创建累加器
     */
    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){{
            put(true,new ArrayList<>());
            put(false,new ArrayList<>());
        }};
    }

    /**
     * 累加方式
     */
    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean,List<Integer>> acc, Integer candidate)->{
            acc.get(isPrime(acc.get(true),candidate)).add(candidate);
        };
    }

    /**
     * 并行操作（实际不行，因为并行需要无序，但该累加器计算是有序的）
     */
    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1,Map<Boolean, List<Integer>> map2)->{
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    /**
     * 转换：将累加器转换为最终结果（此处累加器类型等于结果类型，调用恒等函数）
     */
    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    /**
     * 收集器行为，因为有序，所以另外两个不需要设置
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

}
