package DP;

import com.alibaba.fastjson.JSON;


/**
 * 1049. 最后一块石头的重量 II （01背包问题）
 * <p>
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 * <p>
 *  
 * 示例 1：
 * 输入：stones = [2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * <p>
 * 示例 2：
 * 输入：stones = [31,26,33,21,40]
 * 输出：5
 * <p>
 * 示例 3：
 * 输入：stones = [1,2]
 * 输出：1
 *  
 * <p>
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 100
 * <p>
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 *
 * @author xurongfei
 * @Date 2022/2/4
 */
public class no1049_last_stone_weight_ii {
    /**
     * 01 背包问题
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.56%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了6.40%的用户
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int iter : stones) {
            sum += iter;
        }
        int n = stones.length;
        int m = sum / 2;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (j < stones[i]) {
                    dp[i + 1][j] = dp[i][j];
                } else {
                    dp[i + 1][j] = dp[i][j] || dp[i][j - stones[i]];
                }
            }
        }
        for (int j = m; j >= 0; j--) {
            if (dp[n][j]) {
                return sum - 2 * j;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        no1049_last_stone_weight_ii obj = new no1049_last_stone_weight_ii();
        int[] input = new int[]{31, 26, 33, 21, 40};
        int res = obj.lastStoneWeightII(input);
        System.out.println("input=" + JSON.toJSONString(input) + " res=" + res);
    }
}
