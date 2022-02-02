package DP;

/**
 * 983. 最低票价
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
 * <p>
 * 火车票有三种不同的销售方式：
 * <p>
 * 一张为期一天的通行证售价为 costs[0] 美元；
 * 一张为期七天的通行证售价为 costs[1] 美元；
 * 一张为期三十天的通行证售价为 costs[2] 美元。
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 * <p>
 * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
 * <p>
 * 示例 1：
 * 输入：days = [1,4,6,7,8,20], costs = [2,7,15]
 * 输出：11
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 1 天生效。
 * 在第 3 天，你花了 costs[1] = $7 买了一张为期 7 天的通行证，它将在第 3, 4, ..., 9 天生效。
 * 在第 20 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 20 天生效。
 * 你总共花了 $11，并完成了你计划的每一天旅行。
 * <p>
 * 示例 2：
 * 输入：days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 * 输出：17
 * 解释：
 * 例如，这里有一种购买通行证的方法，可以让你完成你的旅行计划：
 * 在第 1 天，你花了 costs[2] = $15 买了一张为期 30 天的通行证，它将在第 1, 2, ..., 30 天生效。
 * 在第 31 天，你花了 costs[0] = $2 买了一张为期 1 天的通行证，它将在第 31 天生效。
 * 你总共花了 $17，并完成了你计划的每一天旅行。
 * <p>
 * 提示：
 * 1 <= days.length <= 365
 * 1 <= days[i] <= 365
 * days 按顺序严格递增
 * costs.length == 3
 * 1 <= costs[i] <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-cost-for-tickets
 *
 * @author xurongfei
 * @Date 2022/2/2
 */
public class no983_minimum_cost_for_tickets {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了89.58%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了5.11%的用户
     */
    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        int len = days.length;

        int preVal = 0;
        int index = 0;
        for (int i = 1; i < dp.length; i++) {
            int day = index < len ? days[index] : 0;
            if (day == i) {
                for (int j = 0; j < costs.length; j++) {
                    if (j == 0) {
                        dp[day] = dp[day - 1] + costs[j];
                    } else if (j == 1) {
                        dp[day] = Math.min(dp[day], (day >= 7 ? dp[day - 7] : 0) + costs[j]);
                    } else {
                        dp[day] = Math.min(dp[day], (day >= 30 ? dp[day - 30] : 0) + costs[j]);
                    }
                }
                preVal = dp[day];
                index++;
            } else {
                dp[i] = preVal;
            }
        }
        return dp[365];
    }

    public static void main(String[] args) {
        no983_minimum_cost_for_tickets obj = new no983_minimum_cost_for_tickets();
        int res = obj.mincostTickets(new int[]{1, 4}, new int[]{2, 7, 15});
        System.out.println(res);
    }
}
