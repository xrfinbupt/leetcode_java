package Prefix;

import java.util.HashMap;

/**
 * 560. 和为 K 的子数组
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *  
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 * <p>
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 *
 * @author xurongfei
 * @Date 2021/12/4
 */
public class no560_subarray_sum_equals_k {
    /**
     * 执行用时：1901 ms, 在所有 Java 提交中击败了5.02%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了74.69%的用户
     */
    public int subarraySum1(int[] nums, int k) {
        int len = nums.length;
        int result = 0;
        int[] sumArray = new int[len];
        for (int i = 0; i < len; i++) {
            int preVal = (i > 0 ? sumArray[i - 1] : 0);
            sumArray[i] = preVal + nums[i];
        }

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                int temp = sumArray[j] - (i > 0 ? sumArray[i - 1] : 0);
                if (temp == k) {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 参考官方解答
     * 执行用时：21 ms, 在所有 Java 提交中击败了91.92%的用户
     * 内存消耗：40.8 MB, 在所有 Java 提交中击败了73.20%的用户
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int result = 0;
        HashMap<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0, 1);

        int preSum = 0;
        for (int i = 0; i < len; i++) {
            preSum += nums[i];
            if (countMap.containsKey(preSum - k)) {
                result += countMap.get(preSum - k);// 统计[j..i]区间内和为K pre[i] = pre[j-1]+k  => pre[j-1] = pre[i]-k
            }
            countMap.put(preSum, countMap.getOrDefault(preSum, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        no560_subarray_sum_equals_k obj = new no560_subarray_sum_equals_k();
        int res = obj.subarraySum(new int[]{1, 1, 1}, 2);
        System.out.println(res);

        res = obj.subarraySum(new int[]{1, 2, 3}, 2);
        System.out.println(res);

        res = obj.subarraySum(new int[]{-1, 0, 1}, 0);
        System.out.println(res);

        res = obj.subarraySum(new int[]{0, 0, 0}, 0);
        System.out.println(res);

        res = obj.subarraySum(new int[]{1, 2, 1, 2, 1}, 3);
        System.out.println(res);
    }
}
