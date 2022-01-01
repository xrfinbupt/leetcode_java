package DP;

/**
 * 740. 删除并获得点数
 * <p>
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1：
 * 输入：nums = [3,4,2]
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * <p>
 * 示例 2：
 * 输入：nums = [2,2,3,3,3,4]
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 *  
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 *
 * @author xurongfei
 * @Date 2022/1/1
 */
public class no740_delete_and_earn {
    /**
     * 和 198. 打家劫舍 思路一样
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了60.86%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了34.46%的用户
     */
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        int[] sumArray = new int[max + 1];
        for (int i = 0; i < nums.length; i++) {
            sumArray[nums[i]] = sumArray[nums[i]] + nums[i];
        }
        int sum = 0, pre2 = 0, pre1 = 0;
        for (int i = 0; i < sumArray.length; i++) {
            sum = Math.max(pre2 + sumArray[i], pre1);
            pre2 = pre1;
            pre1 = sum;
        }
        return Math.max(pre1, pre2);
    }
}
