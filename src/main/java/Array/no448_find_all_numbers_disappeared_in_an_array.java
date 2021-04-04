package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 *
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 */
public class no448_find_all_numbers_disappeared_in_an_array {
    /**
     * 不需要借助额外空间的思路和https://leetcode-cn.com/problems/set-mismatch类似
     * 自己写的，其他需要借助HashMap的方法就不写了 之前做过类似的了
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        for (int iter : nums) {
            iter = Math.abs(iter);
            int val = nums[iter - 1];
            if (val > 0) {
                nums[iter - 1] *= -1;
            }
        }

        int len = nums.length;
        for (int i = 1; i <= len; i++) {
            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }
        return result;
    }
}
