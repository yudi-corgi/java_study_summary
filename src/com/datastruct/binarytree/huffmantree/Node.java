package com.datastruct.binarytree.huffmantree;

/**
 * @author YUDI
 * @date 2020/4/8 9:52
 * 结点、权重
 */
public class Node implements Comparable<Node> {

    int weight;

    Node left;

    Node right;

    public Node(int weight){
        this.weight = weight;
    }

    public Node(int weight, Node left, Node right) {
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.weight, o.weight);
    }
}
