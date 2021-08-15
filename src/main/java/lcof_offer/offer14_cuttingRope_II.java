package lcof_offer;

import java.math.BigInteger;

/**
 * 剑指 Offer 14- II. 剪绳子 II
 * <p>
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * <p>
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 *  
 * 提示：
 * 2 <= n <= 1000
 * 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
 * <p>
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
 */
public class offer14_cuttingRope_II {
    private BigInteger max = new BigInteger("1");

    /**
     * 执行用时：81 ms, 在所有 Java 提交中击败了7.50%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了5.01%的用户
     */
    public int cuttingRope(int n) {
        max = new BigInteger("1");
        for (int i = 1; i <= n / 2; i++) {
            BigInteger res = calMax(n, i + 1);
            if (res.compareTo(max) > 0) {
                max = res;
            }
        }
        BigInteger res = max.mod(new BigInteger("1000000007"));
        return res.intValue();
    }

    private BigInteger calMax(long n, long count) {
        if (count <= 1 || count >= n) return new BigInteger("1");

        long avg = n / count;
        if (count == 2) {
            long sum = avg * (n - avg);
            return new BigInteger(sum + "");
        } else {
            BigInteger avg1 = new BigInteger(avg + "");
            BigInteger temp = calMax(n - avg, count - 1);
            return avg1.multiply(temp);
        }
    }

    public static void main(String[] args) {
        offer14_cuttingRope_II obj = new offer14_cuttingRope_II();
        int n = 5;
        int res = obj.cuttingRope(n);
        System.out.println("res=" + res + ",n=" + n);

        n = 2;
        res = obj.cuttingRope(n);
        System.out.println("res=" + res + ",n=" + n);

        n = 3;
        res = obj.cuttingRope(n);
        System.out.println("res=" + res + ",n=" + n);

        n = 6;
        res = obj.cuttingRope(n);
        System.out.println("res=" + res + ",n=" + n);

        n = 10;
        res = obj.cuttingRope(n);
        System.out.println("res=" + res + ",n=" + n);

        n = 58;
        res = obj.cuttingRope(n);
        System.out.println("res=" + res + ",n=" + n);
    }
}
