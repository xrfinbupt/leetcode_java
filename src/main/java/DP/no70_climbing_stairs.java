package DP;

/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 *
 * Constraints:
 * 1 <= n <= 45
 *
 * @author xurongfei
 * @Date 2021/12/27
 */
public class no70_climbing_stairs {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了66.01%的用户
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n==1) return 1;

        int pre2 = 1,pre1 = 1;
        int sum = 0;
        for(int i=2;i<=n;i++){
            sum = pre1 + pre2;

            pre2 = pre1;
            pre1 = sum;
        }
        return pre1;
    }
}
