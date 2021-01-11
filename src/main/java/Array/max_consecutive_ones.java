package Array;

/**
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 *
 * 示例 1:
 *
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 *
 * https://leetcode-cn.com/problems/max-consecutive-ones/
 */
public class max_consecutive_ones {
    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums==null || nums.length==0) return 0;

        int maxCount = 0;
        int count = 0;
        for (int iter : nums) {
            if (iter == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }

        maxCount = Math.max(maxCount, count);
        return maxCount;
    }
}
