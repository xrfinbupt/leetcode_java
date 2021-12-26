package DP;

/**
 * 1137. 第 N 个泰波那契数
 *
 * 泰波那契序列 Tn 定义如下： 
 *
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 *
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 *
 * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no1137_n_th_tribonacci_number {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：34.9 MB, 在所有 Java 提交中击败了93.04%的用户
     */
    public int tribonacci(int n) {
        if(n==0) return 0;
        else if(n==1) return 1;
        else if(n==2) return 1;

        int pre3 = 0,pre2 = 1,pre = 1;
        for(int i=3;i<=n;i++){
            int sum = pre3 + pre2+pre;
            pre3 = pre2;
            pre2 = pre;
            pre = sum;
        }
        return pre;
    }
}
