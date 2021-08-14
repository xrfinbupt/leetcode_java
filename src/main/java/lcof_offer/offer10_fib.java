package lcof_offer;

/**
 *
 * 剑指 Offer 10- I. 斐波那契数列
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 *  
 * 提示：
 * 0 <= n <= 100
 *
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 *
 */
public class offer10_fib {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了59.39%的用户
     */
    public int fib(int n) {
        if(n==0 ) return 0;
        if(n==1 ) return 1;

        int pre=0,pre2=1;
        int i=2;
        int sum = 0;
        while(i<=n){
            sum = (pre + pre2)%1000000007;
            i++;
            pre = pre2;
            pre2 = sum;
        }
         //sum = sum % 1000000007;
        if(sum == 1000000008) return 1;
        return sum;
    }
}
