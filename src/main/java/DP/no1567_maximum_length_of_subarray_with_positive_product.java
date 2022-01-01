package DP;

/**
 * 1567. 乘积为正数的最长子数组长度
 * <p>
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 * 请你返回乘积为正数的最长子数组长度。
 * <p>
 * 示例  1：
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,-2,-3,-4]
 * 输出：3
 * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
 * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
 * <p>
 * 示例 3：
 * 输入：nums = [-1,-2,-3,0,1]
 * 输出：2
 * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
 * <p>
 * 示例 4：
 * 输入：nums = [-1,2]
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：nums = [1,2,3,5,-6,4,0,10]
 * 输出：4
 *  
 * <p>
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product
 *
 * @author xurongfei
 * @Date 2022/1/1
 */
public class no1567_maximum_length_of_subarray_with_positive_product {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.69%的用户
     * 内存消耗：55.2 MB, 在所有 Java 提交中击败了43.42%的用户
     */
    public int getMaxLen1(int[] nums) {
        int len = nums.length;
        int maxCount = 0;

        int count = 0;
        int firstCount = -1;
        int lastCount = -1;
        int negativeFlagCount = 0;//0 偶数 1 奇数
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                if (negativeFlagCount % 2 == 1) {
                    maxCount = Math.max(maxCount, Math.max(count - firstCount, count - lastCount));
                }
                firstCount = -1;
                lastCount = -1;
                count = 0;
                negativeFlagCount = 0;
            } else {
                count++;
                if (nums[i] < 0) {
                    if (firstCount == -1) {
                        firstCount = count;
                    }
                    lastCount = count;
                    negativeFlagCount++;
                }
                if ((negativeFlagCount % 2 == 0) && (count > maxCount)) {
                    maxCount = count;
                }
            }
        }
        if (negativeFlagCount % 2 == 1) {
            maxCount = Math.max(maxCount, Math.max(count - firstCount, count - lastCount));
        }

        return maxCount;
    }

    /**
     * 参考官方 动态规划
     * https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product/solution/cheng-ji-wei-zheng-shu-de-zui-chang-zi-shu-zu-ch-3/
     *
     * @param nums
     * @return
     */
    public int getMaxLen(int[] nums) {
        int len = nums.length;
        int maxCount = 0;

        int[] postive = new int[len];
        int[] negative = new int[len];
        postive[0] = nums[0] > 0 ? 1 : 0;
        negative[0] = nums[0] < 0 ? 1 : 0;

        for (int i = 1; i < len; i++) {
            if (nums[i] == 0) {
                postive[i] = negative[i] = 0;
            } else if (nums[i] > 0) {
                postive[i] = postive[i - 1] + 1;
                negative[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
            } else {
                postive[i] = negative[i - 1] > 0 ? negative[i - 1] + 1 : 0;
                negative[i] = postive[i - 1] + 1;
            }
            maxCount = Math.max(maxCount,postive[i]);
        }

        return maxCount;
    }

    public static void main(String[] args) {
        no1567_maximum_length_of_subarray_with_positive_product obj = new no1567_maximum_length_of_subarray_with_positive_product();
        int[] input = new int[]{1, -2, -3, 4};
        int res = obj.getMaxLen(input);
        System.out.println("res=" + res);

        input = new int[]{0, 1, -2, -3, -4};
        res = obj.getMaxLen(input);
        System.out.println("res=" + res);

        input = new int[]{-1, -2, -3, 0, 1};
        res = obj.getMaxLen(input);
        System.out.println("res=" + res);

        input = new int[]{1, 2, 3, 5, -6, 4, 0, 10};
        res = obj.getMaxLen(input);
        System.out.println("res=" + res);

        input = new int[]{-3, 2, 2, 0, 1, 2, -3, 2, 2, 2, 2};
        res = obj.getMaxLen(input);
        System.out.println("res=" + res);
    }
}
