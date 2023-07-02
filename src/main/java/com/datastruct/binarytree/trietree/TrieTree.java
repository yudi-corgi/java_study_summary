package com.datastruct.binarytree.trietree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author CDY
 * @date 2021/7/20
 * @description 字典树（前缀、敏感词匹配，关键词联想）
 */
public class TrieTree {

    // 字典树，Map 存储
    public static Map<String, TrieTreeNode> trieMap ;

    public static void main(String[] args) {
        // 初始化字典树
        TrieTree tt = new TrieTree();
        Set<String> keywords = new HashSet<>();
        keywords.add("天才啊");
        keywords.add("暴力血腥");
        keywords.add("淫荡");
        keywords.add("沙雕");
        keywords.add("屮啊");
        keywords.add("草");
        tt.initTrieTree(keywords);
        // 打印字典树
        for (Map.Entry<String, TrieTreeNode> entry : TrieTree.trieMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        // 匹配敏感词
        String content = "你看到昨天那条消息了吗，简直是沙雕啊.";
        content = tt.filterContent(content, FilterLevel.NOT_FILTERING);
        System.out.println("是否包含敏感词：" + tt.isContainSensitiveWords(content, MatchType.MAXIMUM));
        Set<String> sensitiveWords = tt.getSensitiveWords(content, MatchType.MAXIMUM);
        System.out.println("敏感词数量：" + sensitiveWords.size());
        for (String getKeyword : sensitiveWords) {
            System.out.println(getKeyword);
        }
        // 前缀联想词
        String text = "未完成";
    }

    /**
     * 匹配类型，最小匹配：匹配就返回；最大匹配：遍历完整段文本获取所有匹配的词汇
     */
    enum MatchType{
        MINIMUM("最小敏感度"),
        MAXIMUM("最大敏感度");
        String desc;
        MatchType(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 过滤级别，根据正则表达式过滤文本的特殊符号，如数字、符号、空格等
     */
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
        Map<String, TrieTreeNode> currentMap ;
        // 表示当前节点的子节点
        TrieTreeNode childNode;
        for (String keyWord : keyWords) {
            // 关键词长度 >= 2
            if("".equals(keyWord.trim()) || keyWord.length() <= 1){
                continue;
            }
            word = keyWord;
            // 每次循环重置 currentMap
            currentMap = trieMap;
            for (int i = 0; i < word.length(); i++) {
                String c = String.valueOf(word.charAt(i));
                if ((childNode = currentMap.get(c)) == null) {
                    childNode = new TrieTreeNode();
                    childNode.setWord(c);
                    childNode.setChildWords(new HashMap<>());
                    currentMap.put(c, childNode);
                }
                currentMap = childNode.getChildWords();
                // 关键词遍历到末尾时设置结束标识符
                if(i == word.length() - 1){
                    currentMap.put("isEnd",null);
                }
            }
        }
    }

    /**
     * 过滤文本
     * @param content  初始文本
     * @param level  过滤界别
     * @return  过滤后的文本
     */
    public String filterContent(String content, FilterLevel level){
        Pattern compile = Pattern.compile(level.regex);
        return compile.matcher(content).replaceAll("");
    }

    /**
     * 判断是否包含敏感词
     * @param content 文本
     * @return true or false
     */
    public boolean isContainSensitiveWords(String content, MatchType matchType){
        Set<String> words = getSensitiveWords(content, matchType);
        return !words.isEmpty();
    }

    /**
     * 获取关键词（敏感词匹配）
     * @param content 文本
     * @return matched keyword
     */
    public Set<String> getSensitiveWords(String content, MatchType matchType){
        Set<String> keywords = new HashSet<>();
        if("".equals(content.trim()) || content.length() == 0){
            return keywords;
        }
        // 查询匹配的关键词
        content = content.toLowerCase();
        for (int i = 0; i < content.length(); i++) {
            // 获取关键词长度，若有添加到 Set，并增加对应关键词长度后继续遍历 content
            int length = getSensitiveWordLength(content, i, matchType);
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
     * 获取敏感词长度
     * @param content 文本
     * @return length of keyword
     */
    public int getSensitiveWordLength(String content, int begin, MatchType matchType){
        if("".equals(content.trim()) || content.length() == 0){
            return 0;
        }
        // 计算长度
        String currentWord;
        TrieTreeNode childNode;
        int wordLength = 0;
        boolean isEnd = false;
        Map<String, TrieTreeNode> currentMap = trieMap;
        for (int i = begin; i < content.length(); i++) {
            currentWord = String.valueOf(content.charAt(i));
            if ((childNode = currentMap.get(currentWord)) == null) {
                break;
            }
            wordLength++;
            currentMap = childNode.getChildWords();
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

    /**
     * 获取联想词，此处只做了前缀全量匹配
     * TODO 根据字典树每个词搜索次数(热度)排序联想词、前中后匹配
     * @param content  文本
     * @return  联想词集合，长度默认 10
     */
    public Set<String> getRelationWord(String content){
        Set<String> keywords = new HashSet<>(10);
        if("".equals(content.trim()) || content.length() == 0){
            return keywords;
        }
        // 查询联想词
        boolean match = true;
        String currentWord;
        TrieTreeNode childNode;
        Map<String, TrieTreeNode> currentMap = trieMap;
        StringBuilder sb = new StringBuilder(content);
        // 遍历查询是否有 content 前缀开头的关键词
        for (int i = 0; i < content.length(); i++) {
            currentWord = String.valueOf(content.charAt(i));
            if ((childNode = currentMap.get(currentWord)) == null) {
                match = false;
                break;
            }
        }
        // 存在则遍历 content 前缀开头的关键词并保存
        if(match){
            // content 本身可能也是字典树中的关键词，因此其子节点会包含 isEnd，size > 1 则说明还有其它子节点
            if (!currentMap.containsKey("isEnd") || (currentMap.containsKey("isEnd") && currentMap.size() > 1)) {
                // TODO
            }
        }
        return keywords;
    }

}
