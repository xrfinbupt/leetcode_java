package Array;


import java.util.*;

/**
 * 697. 数组的度
 *
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 * 示例 1:
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 解释:
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 *
 * 示例 2:
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 *
 * 提示：
 * nums.length 在1到 50,000 区间范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 *
 * https://leetcode-cn.com/problems/degree-of-an-array
 */
public class no697_degree_of_an_array {
    /**
     * 我自己写的方法 不是最优的 可能很挫
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray_method1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> targetSet = new HashSet<>();
        int maxCount = 0;
        for (int iter : nums) {
            int val = map.getOrDefault(iter, 0);
            map.put(iter, val + 1);
            if (val + 1 > maxCount) {
                maxCount = val + 1;
                targetSet = new HashSet<>();
                targetSet.add(iter);
            } else if (val + 1 == maxCount) {
                targetSet.add(iter);
            }
        }
        if (maxCount == 1) return 1;

        HashMap<Integer, Integer> startMap = new HashMap<>();
        HashMap<Integer, Integer> endMap = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            if (targetSet.contains(val)) {
                Integer startPos = startMap.get(val);
                if (startPos == null) {
                    startMap.put(val, i);
                } else {
                    endMap.put(val, i);
                }
            }
        }

        int minCount = Integer.MAX_VALUE;
        for (int iter : targetSet) {
            len = endMap.get(iter) - startMap.get(iter) + 1;
            minCount = Math.min(minCount, len);
        }

        return minCount;
    }

    /**
     * 官网的一种解决方法
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray_method2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int len = nums.length;
        HashMap<Integer, Integer> countMap = new HashMap<>();
        HashMap<Integer, Integer> leftMap = new HashMap<>();
        HashMap<Integer, Integer> rightMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            Integer pos = leftMap.get(val);
            if (pos == null) leftMap.put(val, i);
            rightMap.put(val, i);

            int count = countMap.getOrDefault(val, 0);
            countMap.put(val, count + 1);
        }

        int min = Integer.MAX_VALUE;
        Integer max = Collections.max(countMap.values());

        for (Map.Entry<Integer, Integer> iter : countMap.entrySet()) {
            if (iter.getValue().equals(max)) {
                min = Math.min(min, rightMap.get(iter.getKey()) - leftMap.get(iter.getKey()) + 1);
            }
        }
        return min;
    }
}
