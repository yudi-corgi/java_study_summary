package com.datastruct.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 二维数组由外向内螺旋遍历(顺/逆时间)
 *
 * @author YUDI
 * @date 2020/9/21
 */
public class ArraySpiralOrder {

    public static void main(String[] args) {
        int[][] matrix = {
                { 1,  2,  3,  4,  5  },
                { 6,  7,  8,  9,  10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 }
        };
        int[][] matrix2 = {
                { 1,  2,  3,  4,  5  },
                { 6,  7,  8,  9,  10 },
                { 11, 12, 13, 14, 15 },
                { 16, 17, 18, 19, 20 },
                { 21, 22, 23, 24, 25 }
        };

        //顺时针
        List<Integer> clockwiseList = spiralOrder(matrix,true);
        System.out.println(clockwiseList);
        List<Integer> clockwiseList2 = spiralOrder(matrix2,true);
        System.out.println(clockwiseList2);
        //逆时针
        List<Integer> counterclockwiseList = spiralOrder(matrix,false);
        System.out.println(counterclockwiseList);
        List<Integer> counterclockwiseList2 = spiralOrder(matrix2,false);
        System.out.println(counterclockwiseList2);
    }

    /**
     * 顺时针螺旋遍历矩阵
     * @param matrix 矩阵(二维数组)
     * @param flag 获取状态,true:顺时针 false:逆时针
     * @return list集合
     */
    public static List<Integer> spiralOrder(int[][] matrix, boolean flag){

        List<Integer> list = new ArrayList<>();
        // 当二维数组是空或第一个维度是0，直接返回
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        // m是矩阵的行数
        int m = matrix.length;
        //  n是矩阵的列数
        int n = matrix[0].length;
        if (flag) {
            clockwise(matrix, m, n, list);
        }else{
            counterclockwise(matrix, m, n, list);
        }
        return list;
    }

    /**
     * 顺时针螺旋遍历
     * @param matrix 二位数组
     * @param m      行数
     * @param n      列数
     * @param list   list列表存放遍历的元素
     */
    public static void clockwise(int[][] matrix, int m,int n, List<Integer> list){
        // 从外向内逐层遍历矩阵，螺旋循环关键在于内部每个小for循环的条件
        for(int i=0;i<(Math.min(m,n)+1)/2;i++){
            // 从左往右遍历"上边"
            for (int j = i; j < n - i; j++) {
                list.add(matrix[i][j]);
            }
            // 从上往下遍历"右边"
            for (int j = i + 1; j < m - i; j++) {
                list.add(matrix[j][(n - 1) - i]);
            }
            // 从右往左遍历"下边"
            for (int j = i + 1; j < n - i; j++) {
                list.add(matrix[(m - 1) - i][(n - 1) - j]);
            }
            // 从下往上遍历"左边"
            for (int j = i + 1; j < (m - 1) - i; j++) {
                list.add(matrix[(m - 1) - j][i]);
            }
        }
    }

    /**
     * 逆时针螺旋遍历
     * @param matrix 二位数组
     * @param m      行数
     * @param n      列数
     * @param list   list列表存放遍历的元素
     */
    public static void counterclockwise(int[][] matrix, int m,int n, List<Integer> list){
        // 逆时针遍历，对比顺时针变化只在于 list.add() 中数组的索引
        for(int i=0;i<(Math.min(m,n)+1)/2;i++){
            // 从右往左遍历"上边"
            for (int j = i; j < n - i; j++) {
                list.add(matrix[i][(n - 1) - j]);
            }
            // 从上往下遍历"左边"
            for (int j = i + 1; j < m - i; j++) {
                list.add(matrix[j][i]);
            }
            // 从左往右遍历"下边"
            for(int j = i + 1; j < n - i; j++){
                list.add(matrix[(m - 1) - i][j]);
            }
            // 从下往上遍历"右边"
            for (int j = i + 1; j < (m - 1) - i; j++) {
                list.add(matrix[(m - 1) - j][(n-1)-i]);
            }
        }
    }

}
