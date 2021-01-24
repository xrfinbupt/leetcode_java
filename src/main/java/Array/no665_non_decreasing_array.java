package Array;

/**
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 *
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 *
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 *
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  2,4,3,4
 *  4,4,3,4
 *  3,4,2,3
 *  5,7,1,8
 *  1,4,2,3
 * 说明：
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 *
 * https://leetcode-cn.com/problems/non-decreasing-array
 */
public class no665_non_decreasing_array {

    /**
     * 先设置修复current位置，如果不行再试pre位置
     * 错误了几次 大意了
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 1) return true;

        boolean flag = false;
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            int currVal = nums[i];
            int pre = nums[i - 1];
            if (currVal < pre) {

                int next = currVal;
                if (i + 1 < len) {
                    next = nums[i + 1];
                } else {
                    currVal = Integer.MAX_VALUE;
                }


                if (next >= pre) {
                    nums[i] = next;
                } else {
                    nums[i - 1] = currVal;
                    if (i - 2 >= 0 && nums[i - 2] > currVal) {
                        return false;
                    }
                }
                if (flag) return false;
                flag = true;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        no665_non_decreasing_array obj  = new no665_non_decreasing_array();
        boolean result = obj.checkPossibility(new int[]{4,2,3});
        System.out.println("array(4,2,3) result:"+result);
        result = obj.checkPossibility(new int[]{4,2,1});
        System.out.println("array(4,2,1) result:"+result);
        result = obj.checkPossibility(new int[]{2,4,3,4});
        System.out.println("array(2,4,3,4) result:"+result);
        result = obj.checkPossibility(new int[]{1,4,2,3});
        System.out.println("array(1,4,2,3) result:"+result);
        result = obj.checkPossibility(new int[]{5,7,1,8});
        System.out.println("array(5,7,1,8) result:"+result);
        result = obj.checkPossibility(new int[]{1,2,4,5,3});
        System.out.println("array(1,2,4,5,3) result:"+result);
    }
}
