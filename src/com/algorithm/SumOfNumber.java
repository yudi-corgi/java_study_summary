package com.algorithm;

import java.util.*;

/**
 * 在数组中找到和为“特定值”的多个数
 *
 * @author CDY
 * @date 2020/10/12
 */
public class SumOfNumber {

    public static void main(String[] args) {

        int[] arr = {5,12,6,3,7,8,9,2,4,1};
        System.out.print("两数之和：");
        List<List<Integer>> listsOfTwo = sumOfTwoNumbers(arr,13);
        listsOfTwo.forEach(System.out::print);
        System.out.println();

        System.out.print("三数之和：");
        List<List<Integer>> listsOfThree = sumOfThreeNumbers(arr,13);
        listsOfThree.forEach(System.out::print);
        System.out.println();

        System.out.print("三数之和（算法优化）：");
        List<List<Integer>> lists = optimizationSumOfThreeNumbers(arr,13);
        lists.forEach(System.out::print);
        System.out.println();
    }

    /**
     * 两数之和为 target
     *
     * @param arr 整型数组
     * @param target 特定值
     * @return List<List<Integer>>
     */
    public static List<List<Integer>> sumOfTwoNumbers(int[] arr, int target){
        List<List<Integer>> lists= new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>(12);
        for (int i = 0; i < arr.length; i++) {
            // 在 map 中寻找 目标值 - 当前值 的差
            int other = target - arr[i];
            if(map.containsKey(other)){
                lists.add(Arrays.asList(map.get(other),i));
            }
            // 遍历的同时将元素添加到 map 中
            map.put(arr[i],i);
        }
        return lists;
    }

    /**
     * 三数之和为 target，时间复杂度：O(n²)，空间复杂度：O(n)
     * @param arr 整型数组
     * @param target 特定值
     * @return List<List<Integer>>
     */
    public static List<List<Integer>> sumOfThreeNumbers(int[] arr, int target){
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            Map<Integer, Integer> map = new HashMap<>(12);
            // 目标值 - 当前值 的差即为另外两个数的和
            int twoSum = target - arr[i];
            //寻找两数之和等于 twoSum 的组合
            for (int j = i+1; j < arr.length; j++) {
                int other = twoSum - arr[j];
                if (map.containsKey(other)) {
                    lists.add(Arrays.asList(arr[i], other, arr[j]));
                }
                map.put(arr[j], j);
            }
        }
        return lists;
    }

    /**
     * 三数之和为 target 算法优化：双指针法（夹逼法），时间复杂度：O(n²)，空间复杂度：O(1)
     * 双指针法：如下所示，取出 1，目标值减去 1 得出另外两数和 twoSum，j 指向最左侧，k 指向最右侧
     * 若 j+k > twoSum，k 左移, j + k < twoSum ，j 右移，j 和 k 重合则结束遍历
     *          ✔ j         k
     *          1 2 3 4 5 8 9
     * @param arr 整型数组
     * @param target 特定值
     * @return List<List<Integer>>
     */
    public static List<List<Integer>> optimizationSumOfThreeNumbers(int[] arr, int target){
        // 从小到大排序
        Arrays.sort(arr);
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int twoSum = target - arr[i];
            // j 和 k 双指针循环定位，j 在左端，k 在右端
            for (int j = i + 1, k = arr.length - 1; j < arr.length; j++) {
                // j+k > twoSum，则 k 向左移动
                while (j < k && arr[j] + arr[k] > twoSum) {
                    k--;
                }
                // 双指针重合，结束本次循环
                if(j == k){
                    break;
                }
                // 相加等于 twoSum 则保存
                if (arr[j] + arr[k] == twoSum) {
                    lists.add(Arrays.asList(arr[i], arr[j], arr[k]));
                }
                // 当以上条件不满足，则说明 j + k < twoSum，j 右移，即 for 循环的条件
            }
        }
        return lists;
    }
}
