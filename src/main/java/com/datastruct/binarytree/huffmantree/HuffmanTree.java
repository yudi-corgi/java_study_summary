package com.datastruct.binarytree.huffmantree;

import java.util.*;

/**
 * @author YUDI
 * @date 2020/4/8 10:05
 * 哈夫曼树构建
 * 哈夫曼树：在叶子结点和权重确定的情况下，带权路径长度最小的二叉树，也叫最优二叉树
 * 树基础概念：
 * 1、路径：在二叉树中，一个结点到另一个结点所经过的所有结点，称作两个结点间的路径
 * 2、路径长度：从一个结点到另一个结点所经过的“边”的数量（即画了几条线）
 * 3、结点的带权路径长度：指二叉树的根节点到该结点的路径长度和该结点权重的乘积
 * 4、树的带权路径长度(WPL)：所有叶子结点的带权路径长度之和
 * 权重：树的每个结点都有自身的"权重(weight)"，比如下方 main() 中设置好的结点权重数组
 */
public class HuffmanTree {

    private Node root;

    /**
     * 构建哈夫曼树
     *
     * @param weights 结点权重数组
     */
    public void createHuffman(int[] weights) {

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
            Node parent = new Node(left.weight + right.weight, left, right);
            //将父结点添加到队列种
            nodeQueue.add(parent);
        }

        //当只剩最后一个时，即为根结点
        root = nodeQueue.poll();
    }

    /**
     * 前序遍历：即每个结点遍历第一次就输出（根节点->左子树->右子树）
     *
     * @param root 根节点
     */
    public void preOrderTraversal(Node root) {
        if (root != null) {
            System.out.print(root.weight + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    /**
     * 前序遍历（非递归）
     *
     * @param root 根节点
     */
    public void preOrderTraversal2(Node root) {
        Stack<Node> stack = new Stack<>();
        Node pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                System.out.print(pNode.weight + " ");
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                Node node = stack.pop();
                pNode = node.right;
            }
        }
    }

    /**
     * 中序遍历：即每个结点遍历第二次就输出（左子树->根节点->右子树顺序遍历）
     *
     * @param root 根节点
     */
    public void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.weight + " ");
            inOrderTraversal(root.right);
        }
    }

    /**
     * 中序遍历（非递归）
     *
     * @param root 根节点
     */
    public void inOrderTraversal2(Node root) {
        Stack<Node> stack = new Stack<>();
        Node pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else {
                Node node = stack.pop();
                System.out.print(node.weight + " ");
                pNode = node.right;
            }
        }
    }

    /**
     * 后序遍历：即每个结点遍历第三次就输出（左子树->右子树->根节点）
     *
     * @param root 根节点
     */
    public void postOrderTraversal(Node root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.weight + " ");
        }
    }

    /**
     * 后序遍历（非递归）
     *
     * @param root 根节点
     */
    public void postOrderTraversal2(Node root) {
        if(root != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(root);
            while (!s1.empty()) {
                root = s1.pop();
                s2.push(root);
                if (root.left != null) {
                    s1.push(root.left);
                }
                if (root.right != null) {
                    s1.push(root.right);
                }
            }
            while (!s2.empty()) {
                System.out.print(s2.pop().weight + " ");
            }
        }
    }

    /**
     * 层序遍历：即按层次遍历结点输出
     *
     * @param root 根节点
     */
    public void levelTraversal(Node root) {
        List<List<Node>> levels = new ArrayList<>();
        levelBuilder(root, 0, levels);
        levels.forEach(l -> l.forEach( node -> System.out.print(node.weight + " ")));
    }

    private void levelBuilder(Node root, int level, List<List<Node>> levels) {
        // 如果当前层未被创建过，则创建
        if(level >= levels.size()){
            levels.add(new ArrayList<>());
        }
        // 添加元素进当前层的list集合中
        levels.get(level).add(root);
        if (root.left != null) {
            levelBuilder(root.left, level + 1, levels);
        }
        if (root.right != null) {
            levelBuilder(root.right, level + 1, levels);
        }
    }


    /**
     * 层次遍历（非递归）
     *
     * @param root 根节点
     */
    public void levelTraversal2(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.weight + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public static void main(String[] args) {
        //权重数组
        int[] weights = {18, 3, 2, 7, 9, 25};
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.createHuffman(weights);
        System.out.print("前序遍历:");
        huffmanTree.preOrderTraversal(huffmanTree.root);
        System.out.println();
        System.out.print("前序遍历（非递归）:");
        huffmanTree.preOrderTraversal2(huffmanTree.root);
        System.out.println();
        System.out.print("中序遍历:");
        huffmanTree.inOrderTraversal(huffmanTree.root);
        System.out.println();
        System.out.print("中序遍历（非递归）:");
        huffmanTree.inOrderTraversal2(huffmanTree.root);
        System.out.println();
        System.out.print("后序遍历:");
        huffmanTree.postOrderTraversal(huffmanTree.root);
        System.out.println();
        System.out.print("后序遍历（非递归）:");
        huffmanTree.postOrderTraversal2(huffmanTree.root);
        System.out.println();
        System.out.print("层次遍历:");
        huffmanTree.levelTraversal(huffmanTree.root);
        System.out.println();
        System.out.print("层次遍历（非递归）:");
        huffmanTree.levelTraversal2(huffmanTree.root);
        System.out.println();

    }
}
