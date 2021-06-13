package Array;

import java.util.HashSet;
import java.util.Set;

/**
 * 240. 搜索二维矩阵 II
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *  
 * 示例 1：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9 <= target <= 10^9
 *
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 */
public class no240_search_a_2d_matrix_ii {
    /**
     * 执行用时：12 ms, 在所有 Java 提交中击败了16.47%的用户 （??为啥区别那么大呢？er_wei_shu_zu_zhong_de_cha_zhao_lcof）
     * 内存消耗：43.9 MB, 在所有 Java 提交中击败了74.46%的用户
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

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
