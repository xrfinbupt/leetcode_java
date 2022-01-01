package Array;

import java.util.List;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 */
public class no152_maximum_product_subarray {
    /**
     * 动态规划 参考 53. 最大子序和
     * https://leetcode-cn.com/problems/maximum-product-subarray/solution/cheng-ji-zui-da-zi-shu-zu-by-leetcode-solution/
     *
     * @param nums
     * @return
     */
    public int maxProduct1(int[] nums) {
        int max = nums[0];
        int result = max;
        int min = max;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int preMax = max;
            max = Math.max(nums[i], Math.max(preMax * nums[i], min * nums[i]));
            if (max > result) result = max;
            min = Math.min(nums[i], Math.min(preMax * nums[i], min * nums[i]));
        }
        return result;
    }

    /**
     * 前后遍历一遍 遇到0就重新开始
     * 参考网上解答
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int product = 1;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            product = product == 0 ? nums[i] : product * nums[i];
            max = Math.max(max, product);
        }
        product = 1;
        for (int i = len - 1; i >= 0; i--) {
            product = product == 0 ? nums[i] : product * nums[i];
            max = Math.max(max, product);
        }
        return max;
    }

    public static void main(String args[]) {
        no152_maximum_product_subarray obj = new no152_maximum_product_subarray();
        int data[] = new int[]{2, 3, -2, 4};
        int result = obj.maxProduct(data);
        System.out.println("2,3,-2,4 result:" + result);
        System.out.println("-----------");

        data = new int[]{-2, 3, -2, 4};
        result = obj.maxProduct(data);
        System.out.println("-2,3,-2,4 result:" + result);
        System.out.println("-----------");

        data = new int[]{-2, 3, 2, 4};
        result = obj.maxProduct(data);
        System.out.println("-2,3,2,4 result:" + result);
        System.out.println("-----------");

        data = new int[]{-2, 0, -1};
        result = obj.maxProduct(data);
        System.out.println("-2,0,-1 result:" + result);
        System.out.println("-----------");

        data = new int[]{-2, 0};
        result = obj.maxProduct(data);
        System.out.println("-2,0 result:" + result);
        System.out.println("-----------");

        data = new int[]{-4, -3, -2};
        result = obj.maxProduct(data);
        System.out.println("-4,-3,-2 result:" + result);
        System.out.println("-----------");

        data = new int[]{2, -5, -2, -4, 3};
        result = obj.maxProduct(data);
        System.out.println("2,-5,-2,-4,3 result:" + result);
        System.out.println("-----------");
    }
}
