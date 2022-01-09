package DP;

/**
 * 413. 等差数列划分
 * <p>
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的子数组 个数。
 * 子数组 是数组中的一个连续序列。(!!!)
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * <p>
 * 示例 2：
 * 输入：nums = [1]
 * 输出：0
 *  
 * 提示：
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices
 *
 * @author xurongfei
 * @Date 2022/1/9
 */
public class no413_arithmetic_slices {
    /**
     * 参考：https://leetcode-cn.com/problems/arithmetic-slices/solution/hua-dong-chuang-kou-dong-tai-gui-hua-jav-3vpp/
     * 通过画图可以看出 dp[i]为nums[i]结尾的等差数组的子数组个数
     * dp[i]=dp[i-1]+1
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        int dp = 0;
        int result = 0;
        for (int i = 2; i < len; i++) {
            if (nums[i - 1] - nums[i - 2] == nums[i] - nums[i - 1]) {
                int new_dp = dp + 1;
                result += new_dp;
                dp = new_dp;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        no413_arithmetic_slices obj = new no413_arithmetic_slices();
        int[] input = new int[]{3, 5, 7, 9, 11};
        int res = obj.numberOfArithmeticSlices(input);
        System.out.println("res=" + res);
    }
}
