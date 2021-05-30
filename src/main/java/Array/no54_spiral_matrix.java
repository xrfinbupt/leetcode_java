package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 *
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 */
public class no54_spiral_matrix {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了51.02%的用户
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;

        int i = 0;
        int j = 0;
        int dir = 0; // 0 右 1 下 2 左 3 上
        int count = n*m;
        while(count>0){
            int val = matrix[i][j];
            result.add(val);
            matrix[i][j] = 666;

            count--;
            if (dir == 0) {
                if (j >= n - 1 || matrix[i][j+1] == 666) {
                    dir = 1;
                    i++;
                } else {
                    j++;
                }
            } else if (dir == 1) {
                if (i >= m - 1 || matrix[i+1][j] == 666) {
                    dir = 2;
                    j--;
                } else {
                    i++;
                }
            } else if (dir == 2) {
                if (j <= 0 || matrix[i][j-1] == 666) {
                    dir = 3;
                    i--;
                } else {
                    j--;
                }
            } else if (dir == 3) {
                if (i <= 0 || matrix[i-1][j] == 666) {
                    dir = 0;
                    j++;
                } else {
                    i--;
                }
            }
        }
        return result;
    }
}
