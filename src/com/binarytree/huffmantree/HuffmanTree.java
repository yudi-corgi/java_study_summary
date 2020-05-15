package com.binarytree.huffmantree;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author YUDI
 * @date 2020/4/8 10:05
 * 哈夫曼树构建
 * 哈夫曼树：在叶子结点和权重确定的情况下，带权路径长度最小的二叉树，也叫最优二叉树
 * 树基础概念：
 *      1、路径：在二叉树中，一个结点到另一个结点所经过的所有结点，称作两个结点间的路径
 *      2、路径长度：从一个结点到另一个结点所经过的“边”的数量（即画了几条线）
 *      3、结点的带权路径长度：指二叉树的根节点到该结点的路径长度和该结点权重的乘积
 *      4、树的带权路径长度(WPL)：所有叶子结点的带权路径长度之和
 *      权重：树的每个结点都有自身的"权重(weight)"，比如下方 main() 中设置好的结点权重数组
 */
public class HuffmanTree  {

    private Node root;

    /**
     * 构建哈夫曼树
     * @param weights 结点权重数组
     */
    public void createHuffman(int[] weights){

        //优先队列，辅助构建哈夫曼树
        Queue<Node> nodeQueue = new PriorityQueue<>();
        Node[] nodes = new Node[weights.length];

        //初始化 nodes 数组和队列
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(weights[i]);
            nodeQueue.add(nodes[i]);
        }

        nodeQueue.forEach((node) -> System.out.print(node.weight + " "));
        System.out.println();

        //主循环，当队列只剩一个节点时结束
        while (nodeQueue.size() > 1) {
            //从结点队列选择权重最小的结点相加
            Node left = nodeQueue.poll();
            Node right = nodeQueue.poll();
            //相加结果为新结点，并作为两者父结点
            Node parent = new Node(left.weight + right.weight,left,right);
            //将父结点添加到队列种
            nodeQueue.add(parent);
        }

        //当只剩最后一个时，即为根结点
        root = nodeQueue.poll();
    }

    //前序遍历：即每个结点遍历第一次就输出
    public void preorderTraversal(Node root){
        if(root != null){
            System.out.print(root.weight + " ");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }

    }

    //中序遍历：即每个结点遍历第二次就输出
    public void inOrderTraversal(Node root){
        if(root != null){
            inOrderTraversal(root.left);
            System.out.print(root.weight + " ");
            inOrderTraversal(root.right);
        }
    }

    //后序遍历：即每个结点遍历第三次就输出
    public void postOrderTraversal(Node root){
        if(root != null){
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.weight + " ");
        }
    }
    //层次遍历：即按层次遍历结点输出
    public void levelTraversal(Node root){
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.weight + " ");
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        //权重数组
        int[] weights = {18,3,2,7,9,25};
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.createHuffman(weights);
        System.out.print("前序遍历:");
        huffmanTree.preorderTraversal(huffmanTree.root);
        System.out.println();
        System.out.print("中序遍历:");
        huffmanTree.inOrderTraversal(huffmanTree.root);
        System.out.println();
        System.out.print("后序遍历:");
        huffmanTree.postOrderTraversal(huffmanTree.root);
        System.out.println();
        System.out.print("层次遍历:");
        huffmanTree.levelTraversal(huffmanTree.root);
    }
}
