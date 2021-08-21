package lcof_offer;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * <p>
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * <p>
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *  
 * 提示：
 * 0 <= nums.length <= 50000
 * 1 <= nums[i] <= 10000
 * <p>
 * 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
 */
public class offer21_exchange {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了91.76%的用户
     * 内存消耗：46.4 MB, 在所有 Java 提交中击败了32.54%的用户
     */
    public int[] exchange(int[] nums) {
        if (nums == null || nums.length < 2) return nums;

        int first = 0, last = nums.length - 1;
        while (first < last) {
            while (first < nums.length) {
                if (nums[first] % 2 == 1) first++;
                else break;
            }

            while (last > first) {
                if (nums[last] % 2 == 0) last--;
                else break;
            }

            if (first < nums.length && last >= 0 && first < last) {
                int temp = nums[first];
                nums[first] = nums[last];
                nums[last] = temp;
            }
        }

        return nums;
    }
}
