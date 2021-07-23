package com.datastruct.binarytree.trietree;

import java.util.*;

/**
 * @author CDY
 * @date 2021/7/20
 * @description 字典树（前缀、敏感词匹配，关键词联想）
 */
public class TrieTree {

    // 字典树，Map 存储
    public static Map<Object, Object> trieMap ;

    // 匹配深度
    enum MatchType{
        MINIMUM("最小敏感度"),
        MAXIMUM("最大敏感度");
        String desc;
        MatchType(String desc) {
            this.desc = desc;
        }
    }

    // 文本过滤级别
    enum FilterLevel {
        NOT_FILTERING("不过滤", null),
        NORMAL("正常", ""),
        STRICT("严格", "");
        String desc;
        String regex;
        FilterLevel(String desc, String regex) {
            this.desc = desc;
            this.regex = regex;
        }
    }

    /**
     * 初始化字典树
     * @param keyWords 单词、敏感词、联想词等的集合
     */
    public void initTrieTree(Set<String> keyWords){
        // 初始化字典 Map
        trieMap = new HashMap<>(keyWords.size());
        String word;
        // 表示当前节点
        Map<Object, Object> currentMap ;
        // 表示当前节点的子节点
        Map<Object, Object> subMap;
        for (String keyWord : keyWords) {
            // 关键词长度 >= 2
            if("".equals(keyWord.trim()) || keyWord.length() <= 1){
                continue;
            }
            word = keyWord;
            // 每次循环重置 currentMap
            currentMap = trieMap;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                Object o = currentMap.get(c);
                if (o == null) {
                    // 不存在时创建新的子节点
                    subMap = new HashMap<>();
                    currentMap.put(c, subMap);
                    // 将当前节点替换成子节点
                    currentMap = subMap;
                }else{
                    // 存在时直接将节点赋给当前节点
                    currentMap = (Map<Object, Object>) o;
                }
                // 关键词遍历到末尾时设置结束标识符
                if(i == word.length() - 1){
                    currentMap.put("isEnd",null);
                }
            }
        }
    }

    /**
     * 判断是否包含关键词
     * @param content 文本
     * @return true or false
     */
    public boolean isContainKeyword(String content, MatchType matchType){
        Set<String> words = getKeywords(content, matchType);
        return !words.isEmpty();
    }

    /**
     * 获取匹配到的关键词（用于敏感词、联想词获取）
     * @param content 文本
     * @return matched keyword
     */
    public Set<String> getKeywords(String content, MatchType matchType){
        Set<String> keywords = new HashSet<>();
        if("".equals(content.trim()) || content.length() == 0){
            return keywords;
        }
        // 查询匹配的关键词
        content = content.toLowerCase();
        for (int i = 0; i < content.length(); i++) {
            // 获取关键词长度，若有添加到 Set，并增加对应关键词长度后继续遍历 content
            int length = getKeywordLength(content, i, matchType);
            if(length > 0){
                String keyword = content.substring(i, i + length);
                keywords.add(keyword);
                // 在这里判断匹配类型，MINIMUM 匹配到一个就返回
                if(matchType == MatchType.MINIMUM){
                    return keywords;
                }
                i = i + length - 1;
            }
        }
        return keywords;
    }

    /**
     * 获取关键词长度
     * @param content 文本
     * @return length of keyword
     */
    public int getKeywordLength(String content, int begin, MatchType matchType){
        if("".equals(content.trim()) || content.length() == 0){
            return 0;
        }
        // 计算长度
        char currentChar;
        int wordLength = 0;
        boolean isEnd = false;
        Map<Object,Object> currentMap = trieMap;
        for (int i = begin; i < content.length(); i++) {
            currentChar = content.charAt(i);
            if ((currentMap = (Map<Object, Object>) currentMap.get(currentChar)) == null) {
                break;
            }
            wordLength++;
            if(currentMap.containsKey("isEnd")){
                isEnd = true;
                // 判断匹配类型
                if(matchType == MatchType.MINIMUM){
                    break;
                }
            }
        }
        return isEnd ? wordLength : 0;
    }

    public static void main(String[] args) {
        // 初始化字典树
        TrieTree tt = new TrieTree();
        Set<String> keywords = new HashSet<>();
        keywords.add("天才啊");
        keywords.add("二货啊");
        keywords.add("逗逼啊");
        keywords.add("沙雕啊");
        keywords.add("屮啊");
        tt.initTrieTree(keywords);

        // for (Map.Entry<Object, Object> entry : TrieTree.trieMap.entrySet()) {
        //     System.out.println(entry.getKey()+":"+entry.getValue());
        // }

        String content = "你看到昨天那条消息天才啊了吗，小编简直是逗逼啊.";
        System.out.println("是否包含敏感词：" + tt.isContainKeyword(content, MatchType.MAXIMUM));
        Set<String> getKeywords = tt.getKeywords(content, MatchType.MAXIMUM);
        System.out.println("敏感词数量：" + getKeywords.size());
        for (String getKeyword : getKeywords) {
            System.out.println(getKeyword);
        }
        // 过滤文本，去除空格、数字、字母、特殊字符等
        // String content = "你看到昨天那条消息了吗，小编简直是逗逼啊.";
        // String regex = "";
        // Pattern compile = Pattern.compile(regex);
        // String actualContent = compile.matcher(content).replaceAll("");
    }

}
