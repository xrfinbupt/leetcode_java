package Array;

import java.util.BitSet;

/**
 * 645. 错误的集合
 *
 * 集合 S 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。
 * 你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 *
 * 示例：
 * 输入：nums = [1,2,2,4]
 * 输出：[2,3]
 *
 * 提示：
 * 2 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^4
 *
 *进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 *
 * https://leetcode-cn.com/problems/set-mismatch
 */
public class no645_set_mismatch {
    /**
     * 方法1 借助BitSet记录已经出现过的元素
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums_method1(int[] nums) {
        if (nums == null || nums.length <= 1) return null;

        BitSet bitSet = new BitSet();
        int len = nums.length;

        int errorNum = 0;
        int lackNum = 0;
        for (int i = 1; i <= len; i++) {
            if (bitSet.get(nums[i - 1])) {
                errorNum = nums[i - 1];
            } else {
                bitSet.set(nums[i - 1]);
            }
        }

        for (int i = 1; i <= len; i++) {
            if (!bitSet.get(i)) {
                lackNum = i;
            }
        }
        if (errorNum == lackNum) return new int[0];
        return new int[]{errorNum, lackNum};
    }

    /**
     * 时间复杂度O(n) 空间复杂度O(1) 利用原数组空间 (思想很妙)
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums_method2(int[] nums) {
        if (nums == null || nums.length <= 1) return null;

        int errorNum = 0;
        int lackNum = 0;
        for (int val : nums) {
            if (nums[Math.abs(val) - 1] < 0) {
                errorNum = Math.abs(val);
            } else {
                nums[Math.abs(val) - 1] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                lackNum = i + 1;
            }
        }

        if (errorNum == lackNum) return new int[0];
        return new int[]{errorNum, lackNum};
    }

    /**
     * 使用异或运算(我是想不到) 对位运算要很熟练
     * https://leetcode-cn.com/problems/set-mismatch/solution/cuo-wu-de-ji-he-by-leetcode/
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums_method3(int[] nums) {
        int xor = 0, xor0 = 0, xor1 = 0;

        // 将 nums 中所有的数字与 1 到 n 的所有数字做异或运算，得到的结果为 x ∧ y,x 和 y 分别表示 nums 中重复数字和缺失数字
        for (int n : nums)
            xor ^= n;
        for (int i = 1; i <= nums.length; i++)
            xor ^= i;

        // 最右边比特位为 rightmostbit，且使该位置为 1 (比如110，倒数第二位1)
        int rightmostbit = xor & ~(xor - 1);

        // 根据 rightmostbit 不同将数组nums分为两部分。第一部分所有数字的 rightmostbit 为 1，第二部分所有数字的 rightmostbit 为 0。那么 x 和 y 会被分配到不同的部分。此时问题就简化为最开始的简单问题。
        for (int n : nums) {
            if ((n & rightmostbit) != 0)
                xor1 ^= n;
            else
                xor0 ^= n;
        }
        for (int i = 1; i <= nums.length; i++) {
            if ((i & rightmostbit) != 0)
                xor1 ^= i;
            else
                xor0 ^= i;
        }

        // 最后遍历 nums，确定两个数字中哪个为重复数字，哪个为缺失数字
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == xor0)
                return new int[]{xor0, xor1};
        }
        return new int[]{xor1, xor0};
    }

    public static void main(String args[]) {
        no645_set_mismatch obj = new no645_set_mismatch();
        int[] result = obj.findErrorNums_method2(new int[]{2, 2, 3});
        for (int iter : result) {
            System.out.print(iter + " ");
        }
        System.out.println();

        obj.findErrorNums_method2(new int[]{1, 2, 2, 4});
        for (int iter : result) {
            System.out.print(iter + " ");
        }
        System.out.println();
    }
}
