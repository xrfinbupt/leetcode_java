package DP;

/**
 * 198. 打家劫舍(Medium)
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * 链接：https://leetcode-cn.com/problems/house-robber
 */
public class no198_house_robber {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了52.82%的用户
     */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];

        int f = 0, g = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            int preG = g;
            g = Math.max(g, f);
            f = preG + val;
        }
        return Math.max(f, g);
    }

    public static void main(String args[]) {
        no198_house_robber obj = new no198_house_robber();
        System.out.println(obj.rob(new int[]{1, 2, 3, 1}));
        System.out.println(obj.rob(new int[]{11, 2, 3, 5}));
        System.out.println(obj.rob(new int[]{2, 7, 9, 3, 1}));
    }
}
