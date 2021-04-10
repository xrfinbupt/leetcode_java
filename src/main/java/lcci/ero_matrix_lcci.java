package lcci;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 面试题 01.08. 零矩阵
 *
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * 示例 1：
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 * 示例 2：
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/zero-matrix-lcci
 *
 */
public class ero_matrix_lcci {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        HashSet<Integer> rowMap = new HashSet<>();
        HashSet<Integer> colMap = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowMap.add(i);
                    colMap.add(j);
                }
            }
        }
        for (Integer i : rowMap) {
            for (int k = 0; k < n; k++) matrix[i][k] = 0;
        }

        for (Integer j : colMap) {
            for (int k = 0; k < m; k++) matrix[k][j] = 0;
        }
    }

    /**
     * 参考leetcode解答 时间较快 比较占空间
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int rows[] = new int[m];
        int cols[] = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows[i] == 1 || cols[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
