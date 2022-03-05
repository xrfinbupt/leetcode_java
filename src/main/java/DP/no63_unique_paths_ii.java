package DP;

/**
 * 63. 不同路径 II
 * <p>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * 示例 1：
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * <p>
 * 示例 2：
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 * <p>
 * 提示：
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 * <p>
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 *
 * @author xurongfei
 * @Date 2022/3/5
 */
public class no63_unique_paths_ii {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] > 0 || obstacleGrid[m - 1][n - 1] > 0) {
            return 0;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    obstacleGrid[0][0] = -1;
                } else if (obstacleGrid[i][j] > 0) {
                    continue;
                } else {
                    int left_val = 0;
                    int up_val = 0;
                    if (j >= 1 && obstacleGrid[i][j - 1] <= 0) {
                        left_val = obstacleGrid[i][j - 1];
                    }
                    if (i >= 1 && obstacleGrid[i - 1][j] <= 0) {
                        up_val = obstacleGrid[i - 1][j];
                    }
                    obstacleGrid[i][j] = left_val + up_val;
                }
            }
        }

        return 0 - obstacleGrid[m - 1][n - 1];
    }
}
