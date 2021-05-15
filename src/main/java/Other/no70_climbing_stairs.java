package Other;

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
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 */
public class no70_climbing_stairs {
    int path[];
    /**
     * 超时了 需要优化
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if(n==2) return 2;
        else if(n==1) return 1;
        else if(n<=0) return 0;
        return climbStairs(n-1)+climbStairs(n-2);
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n == 2) return 2;
        else if (n == 1) return 1;
        else if (n <= 0) return 0;

        int preN1 = 2;
        int preN2 = 1;
        int sum = 0;
        int index = 3;
        while (index <= n) {
            sum = preN1 + preN2;
            preN2 = preN1;
            preN1 = sum;
            index++;
        }
        return sum;
    }
}
