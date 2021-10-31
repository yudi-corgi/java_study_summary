package com.utils;

import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author CDY
 * @date 2021/10/31
 * @description JMH 基准测试
 */
// 测试类型，此处为吞吐量
@BenchmarkMode(Mode.Throughput)
// 预热操作（迭代三次），指定实际开始运行目标代码前的执行次数，是为了避免 JIT 之类的编译优化影响测试
@Warmup(iterations = 3)
// 度量参数，比如迭代次数、时长等
@Measurement(iterations = 10, time = 5)
// 每个进程的测试线程数
@Threads(8)
// 指 fork 多少个进程进行测试
@Fork(2)
// 测试结果的时间类型
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHUtil {

    // 表示该方法需要进行基准测试，类似 Junit 的 @Test
    @Benchmark
    public void stringAdd(){
        String a = "";
        for (int i = 0; i < 10; i++) {
            a += i;
        }
        System.out.println(a);
    }

    @Benchmark
    public void stringBuilderAdd(){
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            a.append(i);
        }
        System.out.println(a);
    }

    @SneakyThrows
    public static void main(String[] args) {
        // 构建Options，指定要测试的类名称
        Options options = new OptionsBuilder()
                .include(JMHUtil.class.getSimpleName())
                .output("c:/bench_mark.log")
                .build();
        // 根据给定的options构建Runner，Runner是JMH Java API的入口点，执行JMH基准测试
        new Runner(options).run();
    }

}
