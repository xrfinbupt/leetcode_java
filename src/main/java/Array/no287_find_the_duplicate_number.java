package Array;

/**
 * 287. 寻找重复数
 *
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *
 * 示例 3：
 * 输入：nums = [1,1]
 * 输出：1
 *
 * 示例 4：
 * 输入：nums = [1,1,2]
 * 输出：1
 *  
 *
 * 提示：
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *  
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 *
 * @author xurongfei
 * @Date 2021/11/13
 */
public class no287_find_the_duplicate_number {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了97.01%的用户
     * 内存消耗：53.9 MB, 在所有 Java 提交中击败了79.16%的用户
     */
    public int findDuplicate(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int curr = Math.abs(nums[i]);
            int val = nums[curr];
            if(val<0){
                return curr;
            }
            nums[curr] = 0 - nums[curr];
        }
        return 1;
    }
    public static void main(String[] args){
        no287_find_the_duplicate_number obj = new no287_find_the_duplicate_number();
        int res = obj.findDuplicate(new int[]{1,3,4,2,1});
        System.out.println(res);
    }
}
