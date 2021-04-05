package Array;

/**
 * 31. 下一个排列
 *
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * 链接：https://leetcode-cn.com/problems/next-permutation
 *
 */
public class no31_next_permutation {
    /**
     * 分治法 分解成子任务
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.25%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了5.32%的用户
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 1) return;

        boolean flag = sub_nextPermutation(nums, 0);
        if (!flag) {
            int len = nums.length;
            int l = 0, r = len - 1;
            while (l < r) {
                int temp = nums[r];
                nums[r] = nums[l];
                nums[l] = temp;
                l++;
                r--;
            }
        }
    }
    public boolean sub_nextPermutation(int[] nums,int deep) {
        if (deep == nums.length - 2) {
            if (nums[deep] >= nums[deep + 1]) return false;
            else {
                swap(nums,deep,deep+1);
                return true;
            }
        }
        if (sub_nextPermutation(nums, deep + 1)) {
            return true;
        } else if (nums[deep] >= nums[deep + 1]) {
            return false;
        } else {
            // 找到 第一个比nums[deep]大的数替换它
            int r = nums.length -1;
            while (nums[r] <= nums[deep]) r--;
            swap(nums,deep,r);

            int l = deep + 1;
            r = nums.length - 1;

            while (l < r) {
                swap(nums,l,r);
                l++;
                r--;
            }

            return true;
        }
    }
    private void swap(int nums[], int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    /**
     * 官方方法：两遍扫描
     * https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
     *
     * 1 我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
     * 2 同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。
     */
    public void nextPermutation2(int[] nums) {
        if (nums == null || nums.length == 1) return;

        int minPos = -1, maxPos = -1;
        int len = nums.length;
        int r = len - 1;
        while (r > 0 && nums[r - 1] >= nums[r]) r--;
        minPos = r;

        if(minPos == len -1){
            swap(nums, minPos-1, minPos);
            return;
        } else if (minPos == 0) {
            minPos = -1;
        } else {
            minPos--;

            r = len - 1;
            while (r > minPos && nums[r] <= nums[minPos]) r--;
            maxPos = r;

            swap(nums, minPos, maxPos);
        }

        int l = minPos + 1;
        r = len - 1;
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }
}
