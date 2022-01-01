package DP;

/**
 * 918. 环形子数组的最大和
 * <p>
 * 给定一个由整数数组 A 表示的环形数组 C，求 C 的非空子数组的最大可能和。
 * <p>
 * 在此处，环形数组意味着数组的末端将会与开头相连呈环状。（形式上，当0 <= i < A.length 时 C[i] = A[i]，且当 i >= 0 时 C[i+A.length] = C[i]）
 * <p>
 * 此外，子数组最多只能包含固定缓冲区 A 中的每个元素一次。（形式上，对于子数组 C[i], C[i+1], ..., C[j]，不存在 i <= k1, k2 <= j 其中 k1 % A.length = k2 % A.length）
 * <p>
 * 示例 1：
 * 输入：[1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * <p>
 * 示例 2：
 * 输入：[5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * <p>
 * 示例 3：
 * 输入：[3,-1,2,-1]
 * 输出：4
 * 解释：从子数组 [2,-1,3] 得到最大和 2 + (-1) + 3 = 4
 * <p>
 * 示例 4：
 * 输入：[3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * <p>
 * 示例 5：
 * 输入：[-2,-3,-1]
 * 输出：-1
 * 解释：从子数组 [-1] 得到最大和 -1
 *  
 * 提示：
 * -30000 <= A[i] <= 30000
 * 1 <= A.length <= 30000
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-sum-circular-subarray
 *
 * @author xurongfei
 * @Date 2022/1/1
 */
public class no918_maximum_sum_circular_subarray {
    /**
     * 参考
     * https://leetcode-cn.com/problems/maximum-sum-circular-subarray/solution/java-dp-kan-bu-dong-wei-shi-yao-sum-min-x7q53/
     *
     * 执行用时：4 ms, 在所有 Java 提交中击败了86.23%的用户
     * 内存消耗：44.4 MB, 在所有 Java 提交中击败了56.38%的用户
     */
    public int maxSubarraySumCircular(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int sum = nums[0];

        int lastMaxSum = nums[0];
        for (int i = 1; i < len; i++) {
            lastMaxSum = Math.max(lastMaxSum + nums[i], nums[i]);
            max = Math.max(max, lastMaxSum);
            sum += nums[i];
        }

        int min = 0;
        int lastMinSum = 0;
        for (int i = 1; i < len - 1; i++) {
            lastMinSum = Math.min(lastMinSum + nums[i], nums[i]);
            min = Math.min(min, lastMinSum);
        }

        return Math.max(max, sum - min);
    }

    public static void main(String[] args) {
        no918_maximum_sum_circular_subarray obj = new no918_maximum_sum_circular_subarray();
        int res = obj.maxSubarraySumCircular(new int[]{-2, -3, -1});
        System.out.println(res);

        res = obj.maxSubarraySumCircular(new int[]{1, -2, 3, -2, 5, -3, 5});
        System.out.println(res);

        res = obj.maxSubarraySumCircular(new int[]{2, 10, -9, -9, 0, 8, -2, -2, 8, 9});
        System.out.println(res);
    }
}
