package high_frequency.easy;

/**
 * https://leetcode-cn.com/problems/single-number/
 * https://github.com/azl397985856/leetcode/blob/master/problems/136.single-number.md
 */
public class no20_single_number {
    /**
     * 异或的规律
     * 任何数和本身异或则为0
     * 任何数和 0 异或是本身
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            // 异或
            res ^= n;
        }
        return res;
    }
}
