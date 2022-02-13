package DP;

/**
 * 494. 目标和
 * <p>
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 *  
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/target-sum
 *
 * @author xurongfei
 * @Date 2021/12/4
 */
public class no494_target_sum {
    int resultCount = 0;

    /**
     * 执行用时：453 ms, 在所有 Java 提交中击败了34.92%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了58.22%的用户
     */
    public int findTargetSumWays1(int[] nums, int target) {
        resultCount = 0;

        backTracking(nums, 0, target);

        return resultCount;
    }

    /**
     * 01背包问题
     */
    public int findTargetSumWays(int[] nums, int target) {
        int len = nums.length;
        int sum = 0;
        for (int iter : nums) {
            sum += iter;
        }
        if (sum < Math.abs(target)) return 0;
        if ((sum + target) % 2 == 1) return 0;
        int x = (sum + target) / 2;
        int[][] dp = new int[len + 1][x + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= x; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[len][x];
    }

    private void backTracking(int[] nums, int i, int leftVal) {
        if (i >= nums.length) {
            if (leftVal == 0) resultCount++;

            return;
        }
        backTracking(nums, i + 1, leftVal - nums[i]);

        backTracking(nums, i + 1, leftVal + nums[i]);
    }

    public static void main(String[] args) {
        no494_target_sum obj = new no494_target_sum();
        int res = obj.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(res);

        res = obj.findTargetSumWays(new int[]{1, 2, 3, 4, 5, 6, 1, 1}, 3);
        System.out.println(res);

        res = obj.findTargetSumWays(new int[]{1, 1, 1, 1, 1, 1, 1}, 3);
        System.out.println(res);

        res = obj.findTargetSumWays(new int[]{1}, 1);
        System.out.println(res);

        res = obj.findTargetSumWays(new int[]{1, 1}, 0);
        System.out.println(res);
    }
}
