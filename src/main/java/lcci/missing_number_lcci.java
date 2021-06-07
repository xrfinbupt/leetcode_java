package lcci;

/**
 * 面试题 17.04. 消失的数字
 *
 * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 *
 * 注意：本题相对书上原题稍作改动
 *
 * 示例 1：
 * 输入：[3,0,1]
 *
 * 输出：2
 * 示例 2：
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 *
 * 链接：https://leetcode-cn.com/problems/missing-number-lcci
 */
public class missing_number_lcci {
    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了16.50%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了45.79%的用户
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int val = nums[i];
            int absVal = Math.abs(val);
            if (absVal >= len) continue;

            if (val == 0) {
                nums[i] = len;
                if (nums[0] > 0) {
                    nums[0] = 0 - nums[0];
                }
            }
            else if (nums[absVal] == 0) {
                nums[absVal] = 0 - len;
                if (nums[0] > 0) {
                    nums[0] = 0 - nums[0];
                }
            }else{
                nums[absVal] = 0 - nums[absVal];
            }
        }
        for(int i=0;i<len;i++){
            int val = nums[i];
            if(val>0) return i;
        }
        return len;
    }
    public static void main(String args[]){
        missing_number_lcci obj = new missing_number_lcci();
        System.out.println("3,1,0="+obj.missingNumber(new int[]{3,1,0}));
        System.out.println("2,1,0="+obj.missingNumber(new int[]{2,1,0}));
        System.out.println("3,0,1="+obj.missingNumber(new int[]{3,0,1}));
        System.out.println("9,6,4,2,3,5,7,0,1="+obj.missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
        System.out.println("1,2,0,4,3="+obj.missingNumber(new int[]{1,2,0,4,3}));
        System.out.println("1,2,0,5,3="+obj.missingNumber(new int[]{1,2,0,5,3}));
    }
}
