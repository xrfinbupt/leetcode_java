package DP;


import java.util.Arrays;
import java.util.HashMap;

/**
 * 322. 零钱兑换
 * <p>
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * <p>
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 * <p>
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/coin-change
 *
 * @author xurongfei
 * @Date 2021/11/14
 */
public class no322_coin_change {
    int max = Integer.MAX_VALUE;
    HashMap<Integer, Integer> dpMap = new HashMap<>();

    /**
     * 这种方法超时了
     */
    public int coinChange1(int[] coins, int amount) {
        max = Integer.MAX_VALUE;
        bfs(coins, amount, 0, 0);
        return max == Integer.MAX_VALUE ? -1 : max;
    }

    /**
     * 参考官方解答
     * 执行用时：138 ms, 在所有 Java 提交中击败了5.01%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了5.05%的用户
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        dpMap = new HashMap<>();
        for (int iter : coins) {
            dpMap.put(iter, 1);
        }

        int res = dfs(coins, amount);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 执行用时：23 ms, 在所有 Java 提交中击败了18.90%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了20.30%的用户
     */
    public int coinChange3(int[] coins, int amount) {
        int res = dfs2(coins, amount, new int[amount + 1]);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    /**
     * 执行用时：13 ms, 在所有 Java 提交中击败了59.27%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了87.00%的用户
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > i) continue;

                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        int res = dp[amount];
        return res > amount ? -1 : res;
    }

    private int dfs(int[] coins, int amount) {
        if (dpMap.containsKey(amount)) {
            return dpMap.get(amount);
        }
        if (amount == 0) {
            return 0;
        }
        int maxCount = Integer.MAX_VALUE;
        for (int iter : coins) {
            if (iter > amount) continue;

            int res = dfs(coins, amount - iter);
            if (res == Integer.MAX_VALUE) continue;

            maxCount = Math.min(res + 1, maxCount);
        }
        dpMap.put(amount, maxCount);
        return maxCount;
    }

    private int dfs2(int[] coins, int amount, int[] count) {
        if (count[amount] != 0) {
            return count[amount];
        }
        if (amount == 0) {
            return 0;
        }
        int maxCount = Integer.MAX_VALUE;
        for (int iter : coins) {
            if (iter > amount) continue;

            int res = dfs2(coins, amount - iter, count);
            if (res == Integer.MAX_VALUE) continue;

            maxCount = Math.min(res + 1, maxCount);
        }
        count[amount] = maxCount;
        return maxCount;
    }

    private void bfs(int[] coins, int amount, int i, int len) {
        if (amount == 0) {
            max = Math.min(len, max);
            return;
        }
        if (i >= coins.length) {
            return;
        }
        int curr = coins[i];
        int count = amount / curr;
        //System.out.println("i="+i+" count="+count);
        for (int j = 0; j <= count; j++) {
            bfs(coins, amount - j * curr, i + 1, len + j);
        }
    }

    public static void main(String[] args) {
        no322_coin_change obj = new no322_coin_change();
        int res = obj.coinChange(new int[]{1, 2, 5}, 11);
        System.out.println(res);

        res = obj.coinChange(new int[]{2, 5, 6}, 1280);
        System.out.println(res);

        res = obj.coinChange(new int[]{5, 2, 3, 4}, 6000);
        System.out.println(res);

        res = obj.coinChange(new int[]{5, 2, 3, 4}, 10000);
        System.out.println(res);
    }
}
