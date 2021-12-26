package DP;

import java.util.Arrays;

/**
 * 45. 跳跃游戏 II
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 *  
 * 示例 1:
 * 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * 示例 2:
 * 输入: nums = [2,3,0,1,4]
 * 输出: 2
 *  
 * <p>
 * 提示:
 * 1 <= nums.length <= 10^4
 * 0 <= nums[i] <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no45_jump_game_ii {
    /**
     * 执行用时：45 ms, 在所有 Java 提交中击败了18.49%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了67.24%的用户
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            int count = nums[i];
            if (count > 0) {
                for (int j = 1; j <= count; j++) {
                    if (i + j >= len) break;
                    dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
                }
            }
        }
        return dp[len - 1];
    }
}
