package lcof_offer;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 04. 二维数组中的查找
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 *
 * 注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 *
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 */
public class offer4_findNumberIn2DArray {
    /**
     * 方法1 辅助HashMap查找
     *
     * 执行用时：10 ms, 在所有 Java 提交中击败了9.75%的用户
     * 内存消耗：43.6 MB, 在所有 Java 提交中击败了98.47%的用户
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0) return false;

        int m = matrix[0].length;
        if (m == 0) return false;
        Set<Integer> map = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = matrix[i][j];
                if (val == target) return true;
                map.add(val);
            }
        }
        if (map.contains(target)) return true;
        return false;
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：44 MB, 在所有 Java 提交中击败了74.70%的用户
     */
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0) return false;

        int m = matrix[0].length;
        if (m == 0) return false;

        for(int i=0;i<m;i++){
            if(matrix[0][i]<=target){
                if(binarySearch_col(matrix,i,target)) return true;
            }else{
                break;
            }
        }

        for(int i=0;i<n;i++){
            if(matrix[i][0]<=target){
                if(binarySearch_row(matrix[i],target)) return true;
            }else{
                break;
            }
        }
        return false;
    }
    public boolean binarySearch_row(int array[],int target){
        int len = array.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int diff = array[mid] - target;
            if (diff == 0) return true;
            else if (diff > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return false;
    }
    public boolean binarySearch_col(int array[][],int col,int target){
        int len = array.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int diff = array[mid][col] - target;
            if (diff == 0) return true;
            else if (diff > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return false;
    }
}
