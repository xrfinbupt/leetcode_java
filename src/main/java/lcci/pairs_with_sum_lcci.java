package lcci;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 面试题 16.24. 数对和
 *
 * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
 *
 * 示例 1:
 * 输入: nums = [5,6,5], target = 11
 * 输出: [[5,6]]
 *
 * 示例 2:
 * 输入: nums = [5,6,5,6], target = 11
 * 输出: [[5,6],[5,6]]
 *
 * 提示：
 * nums.length <= 100000
 *
 * 链接：https://leetcode-cn.com/problems/pairs-with-sum-lcci
 */
public class pairs_with_sum_lcci {
    /**
     * 执行用时：57 ms, 在所有 Java 提交中击败了14.64%的用户
     * 内存消耗：58.1 MB, 在所有 Java 提交中击败了10.27%的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> pairSums(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums.length == 1) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int iter : nums) {
            int count = map.getOrDefault(iter, 0);
            map.put(iter, count + 1);
        }

        for (int iter : nums) {
            int count1 = map.get(iter);
            if (count1 <= 0) continue;

            int count2 = map.getOrDefault(target - iter, 0);
            if (iter == (target - iter) && count1 > 1) {
                result.add(Arrays.asList(iter, target - iter));
                map.put(iter, count1 - 2);
            } else if (iter != (target - iter) && count1 > 0 && count2 > 0) {
                result.add(Arrays.asList(iter, target - iter));
                map.put(iter, count1 - 1);
                map.put(target - iter, count2 - 1);
            }
        }

        return result;
    }
}
