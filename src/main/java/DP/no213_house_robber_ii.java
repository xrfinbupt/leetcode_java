package DP;

import common.TreeNode;

/**
 * 213. 打家劫舍 II(Medium)
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：nums = [2,3,2]
 * 输出：3
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *
 * 示例 2：
 * 输入：nums = [1,2,3,1]
 * 输出：4
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 */
public class no213_house_robber_ii {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了39.26%的用户
     */
    public int rob1(int[] nums) {
        int result1 = task(nums);

        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        int result2 = task(nums);

        return Math.max(result1, result2);
    }
    private int task(int[] nums){
        boolean fFlag = true;

        int f = 0,g = 0;
        for(int i=0;i<nums.length-1;i++){
            int val = nums[i];
            int preG = g;
            int preF = f;

            g = Math.max(preF,preG);
            f = preG + val;
            if(i>0){
                if(fFlag && (preF == g || preG == g)){
                    fFlag = true;
                }
            }
        }

        if(fFlag) {
            int preG = g;
            g = Math.max(preG, Math.max(f, nums[0]));
        }else{
            int preG = g;
            g = Math.max(preG, Math.max(f, nums[0]));
            f = preG + nums[nums.length - 1];
        }

        return Math.max(f,g);
    }

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了48.50%的用户
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 1) return nums[0];

        int sum0 = 0,pre2Sum=0,pre1Sum=0;
        for(int i=0;i<len-1;i++){
            sum0 = Math.max(pre2Sum+nums[i],pre1Sum);
            pre2Sum = pre1Sum;
            pre1Sum = sum0;
        }

        int sum1 = 0;
        pre2Sum=0;
        pre1Sum=0;
        for(int i=1;i<len;i++){
            sum1 = Math.max(pre2Sum+nums[i],pre1Sum);
            pre2Sum = pre1Sum;
            pre1Sum = sum1;
        }
        return Math.max(sum0,sum1);
    }
    public static void main(String args[]){
        no213_house_robber_ii obj = new no213_house_robber_ii();
        int result = obj.rob(new int[]{2,3,2});
        System.out.println(result);

        result = obj.rob1(new int[]{1,2,3,1});
        System.out.println(result);

        result = obj.rob(new int[]{1});
        System.out.println(result);

        result = obj.rob(new int[]{11,2,3,5,6,21});
        System.out.println(result);
    }
}
