package com.jdk8learn.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 自定义迭代分割器，实现单词计数的并行拆分
 * @author YUDI
 * @date 2020/9/2 1:08
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;
    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // 处理当前字符，若还有则返回 true
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        // 字符串长度小于 10 不再分割
        if (currentSize < 10) {
            return null;
        }
        // 取一半进行分割
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            // 往后遍历找到空格进行分割
            if (Character.isWhitespace(string.charAt(splitPos))) {
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar,splitPos));
                // 设置当前 Spliterator 的字符为分割位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        // 返回剩余要处理的字符数
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        // 配置分隔迭代器属性
        return  ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
