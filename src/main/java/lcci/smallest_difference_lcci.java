package lcci;

import java.util.Arrays;

/**
 * 面试题 16.06. 最小差
 *
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 *
 * 示例：
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出：3，即数值对(11, 8)
 *  
 *
 * 提示：
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间 [0, 2147483647] 内
 *
 * 链接：https://leetcode-cn.com/problems/smallest-difference-lcci
 *
 */
public class smallest_difference_lcci {
    /**
     * 这种方法时间复杂度较高
     * @param a
     * @param b
     * @return
     */
    public int smallestDifference(int[] a, int[] b) {
        int[] shortArray = a;
        int[] longArray = b;
        if (a.length > b.length) {
            shortArray = b;
            longArray = a;
        }
        long minDiff = Integer.MAX_VALUE;
        Arrays.sort(longArray);
        for (int i = 0; i < shortArray.length; i++) {
            long diff = binarySearchDiff(longArray, shortArray[i]);
            if (minDiff > diff) minDiff = diff;
        }
        return (int) minDiff;
    }

    // abs.(diff)
    public long binarySearchDiff(int[] array, int target) {
        int len = array.length;
        int l = 0, r = len - 1;
        long minDiff = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            long diff = array[mid] - target;

            if (diff == 0) {
                return 0;
            } else if (diff > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
            if (minDiff > Math.abs(diff)) minDiff = Math.abs(diff);
        }
        return minDiff;
    }

    /**
     * 方法2
     * @param a
     * @param b
     * @return
     */
    public int smallestDifference2(int[] a, int[] b) {
       Arrays.sort(a);
       Arrays.sort(b);
       int lenA = a.length;
       int lenB = b.length;
       int p1 = 0,p2 = 0;
       long minDiff = Long.MAX_VALUE;

       while(p1<lenA && p2 < lenB){
           long diff = a[p1] - b[p2];
           if(diff == 0) return 0;

           if(diff>0){
               p2++;
           }else{
               p1++;
           }
           if(Math.abs(minDiff) < minDiff) minDiff =Math.abs(minDiff);
       }
       return (int) minDiff;
    }
}
