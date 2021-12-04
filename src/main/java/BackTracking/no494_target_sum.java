package BackTracking;

/**
 * 494. 目标和
 * <p>
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * <p>
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 *  
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/target-sum
 *
 * @author xurongfei
 * @Date 2021/12/4
 */
public class no494_target_sum {
    int resultCount = 0;

    /**
     * 执行用时：453 ms, 在所有 Java 提交中击败了34.92%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了58.22%的用户
     */
    public int findTargetSumWays(int[] nums, int target) {
        resultCount = 0;

        backTracking(nums, 0, target);

        return resultCount;
    }

    private void backTracking(int[] nums, int i, int leftVal) {
        if (i >= nums.length) {
            if (leftVal == 0) resultCount++;

            return;
        }
        backTracking(nums, i + 1, leftVal - nums[i]);

        backTracking(nums, i + 1, leftVal + nums[i]);
    }

    public static void main(String[] args) {
        no494_target_sum obj = new no494_target_sum();
        int res = obj.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(res);

        res = obj.findTargetSumWays(new int[]{1}, 1);
        System.out.println(res);

        res = obj.findTargetSumWays(new int[]{0, 0}, 0);
        System.out.println(res);
    }
}
