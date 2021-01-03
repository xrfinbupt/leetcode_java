package high_frequency.easy;

/**
 * https://github.com/azl397985856/leetcode/blob/master/problems/66.plus-one.md
 * https://leetcode-cn.com/problems/plus-one
 */
public class no10_plus_one {

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
