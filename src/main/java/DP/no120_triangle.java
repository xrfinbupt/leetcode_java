package DP;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * <p>
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * <p>
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * 示例 1：
 * 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 示例 2：
 * 输入：triangle = [[-10]]
 * 输出：-10
 * 提示：
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 * <p>
 * 进阶：
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/triangle
 *
 * @author xurongfei
 * @Date 2022/2/1
 */
public class no120_triangle {
    /**
     * 动态规划 bottom-up
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了96.17%的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了5.30%的用户
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int len = triangle.size();
        int[] dp = new int[len];

        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < len; i++) {
            List<Integer> rows = triangle.get(i);
            int row_len = rows.size();
            int pre1 = dp[0];
            int pre2 = dp[0];
            for (int j = 0; j < row_len; j++) {
                dp[j] = Math.min(pre1, pre2) + rows.get(j);

                pre1 = pre2;
                if (j < row_len - 2) {
                    pre2 = dp[j + 1];
                }
            }
        }

        int min = dp[0];
        for (int i = 1; i < len; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }
}
