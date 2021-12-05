package Other;

/**
 * 581. 最短无序连续子数组
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 * <p>
 * 示例 1：
 * 输入：nums = [2,6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：0
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 *
 * @author xurongfei
 * @Date 2021/12/5
 */
public class no581_shortest_unsorted_continuous_subarray {
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.5 MB, 在所有 Java 提交中击败了79.18%的用户
     */
    public int findUnsortedSubarray(int[] nums) {
        int first = -1;
        int last = -1;

        int len = nums.length;
        int max = nums[0];
        int minInInterval = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (nums[i] < max) {
                if (first == -1) {
                    first = i;
                }
                last = i;
                minInInterval = Math.min(minInInterval,nums[i]);
            } else {
                max = nums[i];
            }
        }

        if (first == -1) return 0;

        /**
         * 用区间内最小值确定 边界
         */
        int count = 0;
        for (int i = first - 1; i >= 0; i--) {
            if (minInInterval < nums[i]) {
                count++;
            } else {
                break;
            }
        }
        return last - first + 1 + count;
    }
}
