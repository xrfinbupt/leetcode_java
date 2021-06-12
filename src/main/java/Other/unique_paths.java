package Other;

/**
 * 62. 不同路径
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 *
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 *
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 109
 *
 * 链接：https://leetcode-cn.com/problems/unique-paths
 */
public class unique_paths {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35 MB, 在所有 Java 提交中击败了86.64%的用户
     */
    public int uniquePaths(int m, int n) {
        int array[][] = new int[m][n];
        for(int i=0;i<n;i++) array[0][i] = 1;
        for(int i=0;i<m;i++) array[i][0] = 1;

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                array[i][j] = array[i-1][j] + array[i][j-1];
            }
        }

        return array[m-1][n-1];
    }
}
