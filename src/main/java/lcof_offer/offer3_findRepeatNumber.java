package lcof_offer;

/**
 * 剑指 Offer 03. 数组中重复的数字
 *
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 * 限制：
 * 2 <= n <= 100000
 *
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 */
public class offer3_findRepeatNumber {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了59.16%的用户
     * 内存消耗：46.2 MB, 在所有 Java 提交中击败了64.34%的用户
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        int len = nums.length;
        for(int i=0;i<len;i++){
            int val = Math.abs(nums[i]);
            val = val==len?0:val;
            int newVal = nums[val];
            if(newVal==0){
                nums[val] = 0 - len;
            } else if (newVal < 0) {
                return val;
            }else{
                nums[val] = 0 - newVal;
            }
        }
        return -1;
    }
}
