package lcci;

/**
 * 面试题 01.07. 旋转矩阵
 *
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 *
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 示例 2:
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
 */
public class rotate_matrix_lcci {
    /**
     * 自己想的一种方法 可以继续优化一下
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        int N = len;
        int i = 0, j = 0;
        while (N > 1) {
            for (int k = 0; k < N - 1; k++) {
                div_rotate(matrix, i, j + k, k, N);
            }
            N -= 2;
            i += 1;
            j += 1;
        }
    }

    public void div_rotate(int[][] matrix, int i, int j, int k, int N) {
        int nextI = i;
        int nextJ = j;
        int next = 0;
        // 右
        int count = 0;
        while (count++ + k < N - 1) nextJ++;
        count = k;
        while (count-- > 0) nextI++;

        next = matrix[nextI][nextJ];
        matrix[nextI][nextJ] = matrix[i][j];

        // 下
        count = 0;
        while (count++ + k < N - 1) nextI++;
        count = k;
        while (count-- > 0) nextJ--;

        int curr = matrix[nextI][nextJ];
        matrix[nextI][nextJ] = next;
        next = curr;

        // 左
        count = 0;
        while (count++ + k < N - 1) nextJ--;
        count = k;
        while (count-- > 0) nextI--;

        curr = matrix[nextI][nextJ];
        matrix[nextI][nextJ] = next;
        next = curr;

        // 上
        count = 0;
        while (count++ + k < N - 1) nextI--;
        count = k;
        while (count-- > 0) nextJ++;

        curr = matrix[nextI][nextJ];
        matrix[nextI][nextJ] = next;
        next = curr;
    }

    /**
     * 官方解答 不好想到
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int len = matrix.length;
        int N = len;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < (N + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[N - 1 - j][i];
                matrix[N - 1 - j][i] = matrix[N - 1 - i][N - 1 - j];
                matrix[N - 1 - i][N - 1 - j] = matrix[j][N - 1 - i];
                matrix[j][N - 1 - i] = temp;
            }
        }
    }
    public static void main(String args[]){
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate_matrix_lcci obj = new rotate_matrix_lcci();
        obj.rotate2(matrix);
    }
}
