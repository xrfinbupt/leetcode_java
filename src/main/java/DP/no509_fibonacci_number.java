package DP;

/**
 * 509. 斐波那契数
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no509_fibonacci_number {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.3 MB, 在所有 Java 提交中击败了15.72%的用户
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int prepre = 0;
        int pre = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = prepre + pre;
            prepre = pre;
            pre = sum;
        }
        return sum;
    }
}
