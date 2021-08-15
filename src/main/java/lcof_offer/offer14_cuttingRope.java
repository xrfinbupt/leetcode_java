package lcof_offer;

/**
 * 剑指 Offer 14- I. 剪绳子
 * <p>
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
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
 * <p>
 * 提示：
 * 2 <= n <= 58
 * 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
 * <p>
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 */
public class offer14_cuttingRope {
    int max = 0;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.1 MB, 在所有 Java 提交中击败了70.92%的用户
     */
    public int cuttingRope(int n) {
        max = 1;
        for (int i = 1; i <= n / 2; i++) {
            int tempMax = calMax(n, i + 1);
            max = Math.max(max, tempMax);
        }
        return max;
    }

    private int calMax(int n, int count) {
        if (count <= 1 || count > n) return 1;

        int avg = n / count;
        if (count == 2) return avg * (n - avg);
        else return avg * calMax(n - avg, count - 1);
    }

    public static void main(String[] args) {
        offer14_cuttingRope obj = new offer14_cuttingRope();
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
