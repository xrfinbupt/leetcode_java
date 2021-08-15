package DFS_BFS;

/**
 * 343. 整数拆分
 * <p>
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * <p>
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * <p>
 * 链接：https://leetcode-cn.com/problems/integer-break
 */
public class no343_integer_break {
    int max;

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了39.92%的用户
     */
    public int integerBreak(int n) {
        for (int i = 1; i <= n / 2; i++) {
            int res = calMax(n, i + 1);
            max = Math.max(res, max);
        }
        return max;
    }

    private int calMax(int n, int count) {
        if (count <= 1 || count >= n) return 1;

        int avg = n / count;
        if (count == 2) return avg * (n - avg);
        else return avg * calMax(n - avg, count - 1);
    }
}
