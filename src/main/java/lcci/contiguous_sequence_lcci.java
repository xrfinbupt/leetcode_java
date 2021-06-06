package lcci;

/**
 * 面试题 16.17. 连续数列
 *
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 *
 * 示例：
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶：
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 链接：https://leetcode-cn.com/problems/contiguous-sequence-lcci
 */
public class contiguous_sequence_lcci {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return Integer.MIN_VALUE;

        int len = nums.length;
        long pre = Integer.MIN_VALUE;
        long result = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            if (pre > result) result = pre;
        }
        return (int) result;
    }
}
