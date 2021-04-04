package Array;

import java.util.Arrays;

/**
 * 414. 第三大的数
 *
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 * 示例 1：
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 *
 * 示例 2：
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 *
 * 示例 3：
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 *  
 * 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 * https://leetcode-cn.com/problems/third-maximum-number
 *
 */
public class no414_third_maximum_number {
    /**
     * 自己的解法
     * @param nums
     * @return
     */
    public int thirdMax_method1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;
        long max3 = Long.MIN_VALUE;
        for (int iter : nums) {
            if (iter >= max1) {
                if (iter != max1) {
                    int temp = iter;
                    max3 = max2;
                    max2 = max1;
                    max1 = temp;
                }
            } else if (iter >= max2) {
                if (iter != max2) {
                    int temp = iter;
                    max3 = max2;
                    max2 = temp;
                }
            } else if (iter >= max3) {
                if (iter != max3) {
                    max3 = iter;
                }
            }
        }
        if (max3 != Long.MIN_VALUE) return (int) max3;
        return (int) max1;
    }

    /**
     * 网上的一种解法
     * @param nums
     * @return
     */
    public int thirdMax_method2(int[] nums) {
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            // 过滤重复和小于第三大的数
            if (third >= num || second == num || first == num) continue;
            if (first < num) {
                third = second;
                second = first;
                first = num;
            } else if (second < num) {
                third = second;
                second = num;
            } else {
                third = num;
            }
        }
        return (int) (third == Long.MIN_VALUE ? first : third);
    }

    public static void main(String args[]) {
        no414_third_maximum_number obj = new no414_third_maximum_number();
        obj.thirdMax_method1(new int[]{1, 2, 2, 5, 3, 5});
    }
}
