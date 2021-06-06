package lcci;

import java.util.Arrays;

/**
 * 面试题 16.21. 交换和
 *
 * 给定两个整数数组，请交换一对数值（每个数组中取一个数值），使得两个数组所有元素的和相等。
 *
 * 返回一个数组，第一个元素是第一个数组中要交换的元素，第二个元素是第二个数组中要交换的元素。若有多个答案，返回任意一个均可。若无满足条件的数值，返回空数组。
 *
 * 示例:
 * 输入: array1 = [4, 1, 2, 1, 1, 2], array2 = [3, 6, 3, 3]
 * 输出: [1, 3]
 * 示例:
 *
 * 输入: array1 = [1, 2, 3], array2 = [4, 5, 6]
 * 输出: []
 *
 * 提示：
 * 1 <= array1.length, array2.length <= 100000
 *
 * 链接：https://leetcode-cn.com/problems/sum-swap-lcci
 *
 */
public class sum_swap_lcci {
    /**
     * 这个也太慢了 TODO
     * 执行用时：21 ms, 在所有 Java 提交中击败了5.00%的用户
     * 内存消耗：49.5 MB, 在所有 Java 提交中击败了90.50%的用户
     */
    public int[] findSwapValues(int[] array1, int[] array2) {
        long sum1=0,sum2=0;
        for(int iter:array1) sum1 += iter;
        for(int iter:array2) sum2 += iter;
        if((sum1+sum2)%2==1) return new int[]{};
        long avg = (sum1+sum2)/2;

        Arrays.sort(array1);
        Arrays.sort(array2);
        int diff1 = (int)(avg - sum1);
        for(int iter:array1){
            int target = iter + diff1;
            if(binarySearch(array2,target)){
                return new int[]{iter,target};
            }
        }
        return new int[]{};
    }
    public boolean binarySearch(int[] array,int target){
        int len = array.length;
        int left = 0,right = len-1;
        while(left<=right){
            int mid = left + (right - left)/2;
            if(array[mid]==target) return true;
            else if(array[mid]>target){
                right  = mid -1;
            }else {
                left = mid +1;
            }
        }
        return false;
    }
}
