package DP;

import com.alibaba.fastjson.JSON;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * <p>
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
 *
 * @author xurongfei
 * @Date 2021/11/13
 */
public class no309_best_time_to_buy_and_sell_stock_with_cooldown {
    /**
     * 执行用时：18 ms, 在所有 Java 提交中击败了7.03%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了99.54%的用户
     */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        int[] dp = new int[len]; //交易的情况
        int[] dp2 = new int[len];//非交易的情况
        int max = 0;

        for (int i = 1; i < len; i++) {
            dp2[i] = dp[i - 1];
            for (int j = i - 1; j >= 0; j--) {
                int diff = prices[i] - prices[j];
                int pre = (j > 0 ? dp2[j - 1] : 0) + diff;
                max = Math.max(max, pre);
            }
            dp[i] = max;
        }

        return max;
    }

    /**
     * 参考官方解答
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/zui-jia-mai-mai-gu-piao-shi-ji-han-leng-dong-qi-4/
     * 执行用时：1 ms, 在所有 Java 提交中击败了93.38%的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了49.00%的用户
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][3]; //交易的情况 0:持有的资产 1：交易后进入冷冻期的资产 2 结束冷冻期的最大资产

        dp[0][0] = 0 - prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }

        return Math.max(dp[len - 1][1], dp[len - 1][2]);
    }

    public static void main(String[] args) {
        no309_best_time_to_buy_and_sell_stock_with_cooldown obj = new no309_best_time_to_buy_and_sell_stock_with_cooldown();
        int[] datas = new int[]{1, 2, 3, 0, 2};
        int res = obj.maxProfit(datas);
        System.out.println("input datas:" + JSON.toJSONString(datas) + " = " + res);

        datas = new int[]{1, 3, 2, 0, 2, 1, 5, 6, 8};
        res = obj.maxProfit(datas);
        System.out.println("input datas:" + JSON.toJSONString(datas) + " = " + res);
    }
}
