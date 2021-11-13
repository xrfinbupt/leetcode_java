package DP;

/**
 * 221. 最大正方形
 * <p>
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *  
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximal-square
 *
 * @author xurongfei
 * @Date 2021/11/13
 */
public class no221_maximal_square {
    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了92.54%的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了51.22%的用户
     */
    public int maximalSquare(char[][] matrix) {
        int res = 0;
        int col = matrix[0].length;
        int row = matrix.length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = 1;

                    int min = Math.min(j > 0 ? dp[i][j - 1] : 0, i > 0 ? dp[i - 1][j] : 0);
                    if (min > 0) {
                        if (matrix[i - min][j - min] == '1') {
                            dp[i][j] = min + 1;
                        }else{
                            dp[i][j] = min;
                        }
                    }
                    res = Math.max(dp[i][j], res);
                }
            }
        }
        return res*res;
    }
    public static void main(String []args){
        no221_maximal_square obj = new no221_maximal_square();
        int res = obj.maximalSquare(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}});

        char[][] datas = new char[][]{{'0','1','1','0','1'},{'1','1','0','1','0'},{'0','1','1','1','0'},{'1','1','1','1','0'},{'1','1','1','1','1'},{'0','0','0','0','0'}};
        res = obj.maximalSquare(datas);
        System.out.println(res);
    }
}
