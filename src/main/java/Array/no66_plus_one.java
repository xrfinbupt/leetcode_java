package Array;

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 *
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 *
 *
 * https://github.com/azl397985856/leetcode/blob/master/problems/66.plus-one.md
 * https://leetcode-cn.com/problems/plus-one
 */
public class no66_plus_one {

    /**
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0) return digits;

        int len = digits.length;
        digits[len-1]++;
        for(int i=len-1;i>=1;i--){
            if(digits[i]>9){
                digits[i] -=10;
                digits[i-1]++;
            }
        }
        if(digits[0]>9){
            int[] result = new int[len+1];
            result[0] = 1;
            digits[0] -=10;
            for(int i=1;i<=len;i++){
                result[i] = digits[i-1];
            }
            return result;
        }
        return digits;
    }
}
