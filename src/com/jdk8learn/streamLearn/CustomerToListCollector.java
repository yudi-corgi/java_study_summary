package com.jdk8learn.streamLearn;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 实现 Collector 收集器接口，复现 toList()
 * @author YUDI
 * @date 2020/8/27 0:43
 */
public class CustomerToListCollector<T> implements Collector<T, List<T>,List<T>> {

    /**
     * 供应源：建立结果容器(空的累加器)，起始函数，创建 List
     */
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    /**
     * 累加器：将流中元素累加结果容器
     */
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    /**
     * 并行操作下，合并子结果容器
     */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2)->{
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * 转换函数：将累加器转换为最终结果
     * 此处累加器是 list，返回的也是 list，所以无需转换
     */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    /**
     * 定义收集器行为
     * UNORDERED——归约结果不受流中项目的遍历和累积顺序的影响
     * CONCURRENT——accumulator 函数可以从多个线程同时调用，且该收集器可以并行归约流(仅在无序时可并行规约流)。
     * IDENTITY_FINISH——这表明完成器方法(finisher)返回的函数是一个恒等函数，可以跳过。
     * 这种情况下，累加器对象将会直接用作归约过程的最终结果。
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }
}
