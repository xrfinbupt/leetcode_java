package Array;

/**
 * 27. 移除元素
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2]
 * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 *
 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3]
 * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 *  
 * 提示：
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 50
 * 0 <= val <= 100
 *
 * 链接：https://leetcode-cn.com/problems/remove-element
 */
public class no27_remove_element {
    /**
     * 双指针
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.8 MB, 在所有 Java 提交中击败了87.92%的用户
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length <= 0) return 0;
        if (nums.length == 1 && nums[0] == val) return 0;

        int result = nums.length;
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            while (l < len && nums[l] != val) l++;
            if (l >= len) return result;

            while (r >= l && nums[r] == val){
                result--;
                r--;
            }
            if (r < l) return result;

            result--;
            nums[l] = nums[r];
            l++;
            r--;
        }

        return result;
    }
}
