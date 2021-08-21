package Other;

import com.alibaba.fastjson.JSON;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * <p>
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * <p>
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 * <p>
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 */
public class offer29_spiralOrder {

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.44%的用户
     * 内存消耗：39.9 MB, 在所有 Java 提交中击败了26.86%的用户
     */
    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return new int[]{};

        int n = matrix[0].length;
        if (n == 0) return new int[]{};

        int[] res = new int[m * n];
        int index = 0, i = 0, j = 0, dir = 0;
        int total = m * n;

        int mUp = 0, mBottom = m - 1;
        int nLeft = 0, nRight = n - 1;
        while (index < total) {
            res[index] = matrix[i][j];

            if (dir == 0) {
                // right
                if (j < nRight) {
                    j++;
                } else {
                    mUp++;

                    i++;
                    dir = 1;
                }
            } else if (dir == 1) {
                // down
                if (i < mBottom) {
                    i++;
                } else {
                    nRight--;

                    j--;
                    dir = 2;
                }
            } else if (dir == 2) {
                // left
                if (j > nLeft) {
                    j--;
                } else {
                    mBottom--;

                    i--;
                    dir = 3;
                }
            } else if (dir == 3) {
                // up
                if (i > mUp) {
                    i--;
                } else {
                    nLeft++;

                    j++;
                    dir = 0;
                }
            }

            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        offer29_spiralOrder obj = new offer29_spiralOrder();
        int[] res = obj.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        System.out.println(JSON.toJSONString(res));
    }
}
