package Array;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 提示：
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/
 */
public class no122_best_time_to_buy_and_sell_stock_ii {
    /**
     * 贪心: 贪心算法只能用于计算最大利润，计算的过程并不是实际的交易过程
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    /**
     * 方法：动态规划
     *
     * 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
     * 定义状态：
     * dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，
     * dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0 开始）
     *
     * @param prices
     * @return
     */
    public int maxProfit_dp(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int len = prices.length;
        int array[][] = new int[len][2];
        array[0][0] = 0;
        array[0][1] = 0 - prices[0];
        for (int i = 1; i < len; i++) {
            array[i][0] = Math.max(array[i - 1][0], array[i - 1][1] + prices[i]);
            array[i][1] = Math.max(array[i - 1][0] - prices[i], array[i - 1][1]);
        }

        return array[len - 1][0];
    }

    /**
     * 方法：动态规划(去掉多余的内存)
     *
     * @param prices
     * @return
     */
    public int maxProfit_dp2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int len = prices.length;
        int pre0 = 0;
        int pre1 = 0 - prices[0];
        for (int i = 1; i < len; i++) {
            pre0 = Math.max(pre0, pre1 + prices[i]);
            pre1 = Math.max(pre0 - prices[i], pre1);
        }

        return pre0;
    }

}
