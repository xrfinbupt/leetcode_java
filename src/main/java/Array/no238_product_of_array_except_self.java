package Array;

import java.util.List;

/**
 * 238. 除自身以外数组的乘积
 *
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 */
public class no238_product_of_array_except_self {
    /**
     * 超出时间限制
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int result[] = new int[len];
        int temp[] = new int[len];

        temp[0] = nums[0];
        temp[len - 1] = 1;
        for (int i = 1; i < len - 1; i++) {
            temp[i] = temp[i - 1] * nums[i];
        }
        for (int i = len - 1; i > 0; i--) {
            result[i] = temp[i - 1];
            for (int j = i-2; j >= 0; j--) {
                temp[j] = temp[j]*nums[i];
            }
            temp[len - 1] *= nums[i];
        }
        result[0] = temp[len-1];

        return result;
    }

    /**
     * 左右乘积列表(参考官方解答)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int len = nums.length;
        int result[] = new int[len];
        int left[] = new int[len];
        int right[] = new int[len];

        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i-1];
        }
        right[len-1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i+1];
        }
        for (int i = 0; i < len; i++) {
            result[i] = left[i]*right[i];
        }

        return result;
    }

    /**
     * 参考官方解答
     * 空间复杂度O(1)
     * @param nums
     * @return
     */
    public int[] productExceptSelf3(int[] nums) {
        int len = nums.length;
        int result[] = new int[len];

        result[0] = 1;
        for (int i = 1; i < len; i++) {
            result[i] = result[i - 1] * nums[i-1];
        }
        int r = 1;
        for (int i = len-1; i >=0; i--) {
            result[i] = result[i]*r;
            r = r * nums[i];
        }

        return result;
    }
    public static void main(String args[]) {
        no238_product_of_array_except_self obj = new no238_product_of_array_except_self();
        int data[] = new int[]{1,2,3,4,5};
        int[] result = obj.productExceptSelf3(data);
        for(int iter:result) {
            System.out.print(iter + " ");
        }
        System.out.println("-----------");
    }
}
