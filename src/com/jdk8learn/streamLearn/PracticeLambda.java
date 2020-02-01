package com.jdk8learn.streamLearn;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version 2019/8/23
 **/
public class PracticeLambda {

    final static List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args){

        final List<String> uppercaseNames = new ArrayList<String>();

        //forEach
        friends.forEach(name -> uppercaseNames.add(name.toUpperCase()));
        //System.out.println(uppercaseNames);
        final Predicate<String> startsWithN1 = name -> name.startsWith("N");

        //map
        friends.stream();
        friends.stream().map(String::toUpperCase).forEach(System.out::println);


        //调用高阶函数
        List<String> startsWithN =
                friends.stream().filter(checkIfStartWith("N")).collect(Collectors.toList());
        final long count = friends.stream().filter(checkIfStartWith("N")).count();
        System.out.println(count );

        //自定义 Function<T,R>
        final Function<String,Predicate<String>> startWithLetter =
                (String letter) -> {
                    Predicate<String> predicate = name -> name.startsWith(letter);
                    return predicate;
                };

        final Function<String,Predicate<String>> startWithLetter2 =
                (String letter) -> (String name) -> name.startsWith(letter);

        final Function<String,Predicate<String>> startWithLetter3 =
                 letter ->  name -> name.startsWith(letter);
        startsWithN = friends.stream().filter(startWithLetter3.apply("N")).collect(Collectors.toList());
        System.out.println(startsWithN );

        //挑选元素
        pickName(friends,"N");

        //集合归约
        //获取所有名称长度的总和
        Integer charNums = friends.stream().mapToInt(name->name.length()).sum();
        //获取名称长度最大的元素
        final Optional<String> aLongName = friends.stream()
                .reduce((name1,name2) -> name1.length() >= name2.length() ? name1 : name2 );
        //通过标识获取
        final String bLongName = friends.stream()
                .reduce("CDY",(name1,name2) -> {
                    return name1.length() >= name2.length() ? name1 : name2;
                } );
        System.out.println(charNums + " :: " + (aLongName.orElse("NO NAME HERE!")) + " :: " + bLongName);

        //获取A开头的 Optional<T>
        final Optional<String> opt = friends.stream().filter(name -> name.startsWith("A")).findFirst();
        System.out.println(opt.isPresent());

        //连接元素
        String lianjie = String.join(" == ",friends);
        System.out.println(lianjie);

        final Optional<String> allName = friends.stream().reduce((name1,name2)->{
            String name = name1 + " == " + name2;
            return name;
        });
        System.out.println(allName);

        System.out.println(friends.stream().map(String::toUpperCase).collect(Collectors.joining(",")));


        //字符串 流化
        final String str = "CDYXXX1";
        str.chars().forEach(PracticeLambda::intToChar);
        System.out.println();
        //int转化为char
        str.chars().mapToObj(ch -> (char) ch).forEach(System.out::print);
        System.out.println();
        //filter 使用
        str.chars().filter(Character::isDigit).forEach(PracticeLambda::intToChar);


        //Comparator 接口
        final List<Computer> computerList = getComputerList();
        //Collections.sort(computerList);

    }

    /**
     * ASCII数字转换为字符
     * @param ch
     */
    public static void intToChar(int ch){
        System.out.print((char)ch);
    }

    /**
     * 定义 filter 函数参数 -> Predicate<T>
     * 高阶函数 -》 通过带参数方法获取 lambda 表达式
     * @param letter
     * @return  Predicate<String>
     */
    public static Predicate<String> checkIfStartWith(String letter){
        return name -> name.startsWith(letter);
    }

    /**
     * 挑选元素，获取开头为 letter 的第一个元素
     * @param friends
     * @param letter
     */
    public static void pickName(final List<String> friends,String letter){

        final Optional<String> name = friends.stream().filter(checkIfStartWith(letter)).findFirst();
        System.out.println(String.format("A name starting with %s: %s", letter, name.orElse("No name found")));
    }

    public static List<Computer> getComputerList(){
        final List<Computer> computerList = new ArrayList<>();
        Computer computer1 = new Computer();
        computer1.setName("炫龙毁灭者DC");
        computer1.setPrice("3999");
        computer1.setModel("未知");
        computer1.setBrand("炫龙");
        Computer computer2 = new Computer();
        computer2.setName("深海幽灵Z2 Air");
        computer2.setPrice("6099");
        computer2.setModel("深海幽灵X");
        computer2.setBrand("机械革命");
        computerList.add(computer1);
        computerList.add(computer2);
        return computerList;
    }

}
