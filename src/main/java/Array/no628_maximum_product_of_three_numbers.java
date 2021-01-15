package Array;

import java.util.Arrays;

/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: 6
 *
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 *
 * 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * https://leetcode-cn.com/problems/maximum-product-of-three-numbers
 *
 */
public class no628_maximum_product_of_three_numbers {
    /**
     * https://leetcode-cn.com/problems/maximum-product-of-three-numbers/solution/san-ge-shu-de-zui-da-cheng-ji-by-leetcode/
     * <p>
     * 我们实际上只要求出数组中最大的三个数以及最小的两个数,然后计算即可(很重要的思想 我竟然没想到)
     *
     * @param nums
     * @return
     */
    public int maximumProduct_method1(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[len - 1] * nums[len - 2] * nums[len - 3], nums[len - 1] * nums[0] * nums[1]);
    }

    public int maximumProduct_method2(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int iter : nums) {
            if (iter >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = iter;
            } else if (iter >= max2) {
                max3 = max2;
                max2 = iter;
            } else if (iter >= max3) {
                max3 = iter;
            }
            if (iter <= min1) {
                min2 = min1;
                min1 = iter;
            } else if (iter <= min2) {
                min2 = iter;
            }
        }
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }

    public static void main(String args[]) {
        no628_maximum_product_of_three_numbers obj = new no628_maximum_product_of_three_numbers();
        int result = obj.maximumProduct_method2(new int[]{-1, -2, -3});
        System.out.println(result);
        result = obj.maximumProduct_method2(new int[]{1, 2, 3});
        System.out.println(result);
    }
}
