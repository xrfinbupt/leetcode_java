package DP;

/**
 * @author xurongfei
 * @Date 2022/1/8
 */
public class no714_best_time_to_buy_and_sell_stock_with_transaction_fee {
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = 0 - prices[0];
        for (int i = 1; i < len; i++) {
            int dp0 = dp[0];
            int dp1 = dp[1];

            dp[0] = Math.max(dp0, dp1 + prices[i] - fee);
            dp[1] = Math.max(dp1, dp0 - prices[i]);
        }
        return dp[0];
    }
}
