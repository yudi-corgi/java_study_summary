package com.datastruct.binarytree.trietree;

import lombok.Data;

import java.util.Map;

/**
 * 字典树节点
 * @author YUDI-Corgi
 */
@Data
public class TrieTreeNode {

    private String word;

    private Map<String, TrieTreeNode> childWords;

}
