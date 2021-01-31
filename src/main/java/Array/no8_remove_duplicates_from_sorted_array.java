package Array;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 */
public class no8_remove_duplicates_from_sorted_array {

    /**
     * 方法1 常规思路 比较耗时
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int orgLen = nums.length;
        int delNum = 0;
        int pre = nums[0];
        int rl = -1;
        for (int i = 1; i < orgLen - delNum; ) {
            if (nums[i] == pre) {
                if (rl == -1) rl = i;
            } else {
                // del
                if (rl != -1) {
                    //重新调整i
                    int offset = i - rl;
                    for (int ii = i; ii < orgLen - delNum; ii++) {
                        nums[ii - offset] = nums[ii];
                    }
                    delNum += (i - rl);

                    i = i - offset;
                }
                pre = nums[i];
                rl = -1;
            }
            i++;
        }
        if (rl != -1) {
            //重新调整i
            int offset = orgLen - delNum - rl;
            delNum += offset;
        }
        return orgLen - delNum;
    }

    /**
     * 方法2 双指针方法 思路很精妙
     * <p>
     * 使用快慢指针来记录遍历的坐标。
     * 开始时这两个指针都指向第一个数字
     * 如果两个指针指的数字相同，则快指针向前走一步
     * 如果不同，则两个指针都向前走一步
     * 当快指针走完整个数组后，慢指针当前的坐标加 1 就是数组中不同数字的个数
     */
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int pslow = 0;
        int pfast = 0;
        int orgLen = nums.length;
        while (pfast < orgLen) {
            if (nums[pslow] == nums[pfast]) {
                pfast++;
            } else {
                pslow++;
                nums[pslow] = nums[pfast];
                pfast++;
            }
        }
        return pslow + 1;
    }

    public static void main(String args[]) {
        no8_remove_duplicates_from_sorted_array obj = new no8_remove_duplicates_from_sorted_array();
        int[] nums = new int[]{1, 1, 1, 1, 1, 2, 2, 2, 3};
        int len = obj.removeDuplicates2(nums);
        System.out.println(len);
    }
}
