package Array;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 *
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 *
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
public class no4_median_of_two_sorted_arrays {
    /**
     * 官方解答真简洁
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2,nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0,right = m;
        int mid1 = 0,mid2 = 0;
        while(left <= right){
            int i = (left + right)/2;
            int j = (m+n+1)/2 - i;
            int num_i1 = i==0?Integer.MIN_VALUE:nums1[i-1];
            int num_i = i==m?Integer.MAX_VALUE:nums1[i];
            int num_j1 = j==0?Integer.MIN_VALUE:nums2[j-1];
            int num_j = j==n?Integer.MAX_VALUE:nums2[j];
            if(num_i1<= num_j){
                mid1 = Math.max(num_i1,num_j1);
                mid2 = Math.min(num_i,num_j);
                left = i+1;
            }else{
                right = i-1;
            }

        }
        return (m+n)%2==0?(mid1+mid2)/2.0:mid1;
    }

    public static void main(String args[]){
        no4_median_of_two_sorted_arrays obj = new no4_median_of_two_sorted_arrays();
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{2,3},new int[]{2}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{2,3},new int[]{}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{},new int[]{2}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{1},new int[]{}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{3,4},new int[]{3,4}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{3,4},new int[]{1,2}));
        System.out.println("result:" + obj.findMedianSortedArrays(new int[]{0,0},new int[]{0,0}));
    }
}
