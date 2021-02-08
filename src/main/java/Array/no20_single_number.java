package Array;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
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