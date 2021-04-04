package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据
 *
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * 找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 * https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 */
public class no442_find_all_duplicates_in_an_array {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        for (int iter : nums) {
            iter = Math.abs(iter);
            int val = nums[iter - 1];
            if (val > 0) {
                nums[iter - 1] *= -1;
            } else {
                result.add(iter);
            }
        }

        return result;
    }
}
