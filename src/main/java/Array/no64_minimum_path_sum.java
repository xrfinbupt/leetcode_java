package Array;

/**
 * 64. 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 *
 */
public class no64_minimum_path_sum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int path[][] = new int[m][n];

        path[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int up = i - 1 >= 0 ? path[i - 1][j] : Integer.MAX_VALUE;
                int left = j - 1 >= 0 ? path[i][j - 1] : Integer.MAX_VALUE;
                path[i][j] = grid[i][j] + Math.min(up, left);
            }
        }

        return path[m - 1][n - 1];
    }
}
