package DP;

/**
 * 746. 使用最小花费爬楼梯
 *
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 *
 * 示例 1：
 * 输入：cost = [10,15,20]
 * 输出：15
 * 解释：你将从下标为 1 的台阶开始。
 * - 支付 15 ，向上爬两个台阶，到达楼梯顶部。
 * 总花费为 15 。
 *
 * 示例 2：
 * 输入：cost = [1,100,1,1,1,100,1,1,100,1]
 * 输出：6
 * 解释：你将从下标为 0 的台阶开始。
 * - 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
 * - 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
 * - 支付 1 ，向上爬一个台阶，到达楼梯顶部。
 * 总花费为 6 。
 *  
 * 提示：
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 *
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 *
 * @author xurongfei
 * @Date 2021/12/27
 */
public class no746_min_cost_climbing_stairs {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了86.49%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了54.55%的用户
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int pre2 = 0,pre1=0;
        int sum = 0;
        for(int i=2;i<=len;i++){
            sum = Math.min(pre2+cost[i-2],pre1+cost[i-1]);
            pre2 = pre1;
            pre1 = sum;
        }
        return pre1;
    }
}
