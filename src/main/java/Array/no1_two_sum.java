package Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * <p>
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * <p>
 * 提示：
 * 2 <= nums.length <= 103
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * <p>
 * https://leetcode-cn.com/problems/two-sum
 */
public class no1_two_sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {

            Integer preValue = map.get(target - nums[i]);
            if (preValue == null) {
                map.put(nums[i], i);
                continue;
            } else {
                if (preValue == i) continue;
                return new int[]{preValue, i};
            }
        }
        return null;
    }

    /**
     * 如果不是返回数组下标 可以用这种方法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - 1) / 2;
            int val = nums[i] + nums[j];
            int val2 = nums[i] + nums[mid];
            int val3 = nums[mid] + nums[j];
            if (val2 > target) {
                j = mid - 1;
            } else if (val3 < target) {
                i = mid + 1;
            } else if (val > target) {
                j--;
            } else if (val < target) {
                i++;
            } else {
                return new int[]{i, j};
            }
        }
        return null;
    }
}
