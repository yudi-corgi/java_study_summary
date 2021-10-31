package com.jdk8learn.spliterator;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 分隔迭代器
 * @author YUDI
 * @date 2020/9/2 0:52
 */
public class AllTest {

    private static int countWord(Stream<Character>stream){
        WordCounter counter = stream.reduce(new WordCounter(0,true),WordCounter::accumulate,WordCounter::combine);
        return counter.getCounter();
    }

    public static void main(String[] args) {
        final String SENTENCE = " Nel mezzo del cammin di nostra vita " +
                                "mi ritrovai in una selva oscura" +
                                " ché la dritta via era smarrita ";
        //顺序流
        Stream<Character> stream = IntStream.range(0,SENTENCE.length()).mapToObj(SENTENCE::charAt);
        System.out.println("顺序流计算单词数量为：" + countWord(stream));
        //并行流
        Stream<Character> parallelStream = StreamSupport.stream(new WordCounterSpliterator(SENTENCE),true);
        System.out.println("并行流计算单词数量为：" + countWord(parallelStream));
    }

}
