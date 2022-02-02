package DP;

/**
 * 931. 下降路径最小和
 * <p>
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * <p>
 * 示例 1：
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 * <p>
 * 示例 2：
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 *  
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-falling-path-sum
 *
 * @author xurongfei
 * @Date 2022/2/2
 */
public class no931_minimum_falling_path_sum {
    /**
     * 动态规划 bottom-up
     */
    public int minFallingPathSum(int[][] matrix) {
        int len = matrix.length;

        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = matrix[0][i];
        }

        for (int i = 1; i < len; i++) {
            int pre1 = dp[0];
            int pre2 = dp[0];
            int[] temp = matrix[i];
            for (int j = 0; j < len; j++) {
                dp[j] = Math.min(pre1, pre2) + temp[j];

                pre1 = pre2;
                if (j + 1 < len) {
                    dp[j] = Math.min(dp[j], dp[j + 1] + temp[j]);
                    pre2 = dp[j + 1];
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            result = Math.min(result, dp[i]);
        }
        return result;
    }
}
