package lcci;

/**
 * 面试题 17.19. 消失的两个数字
 *
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 *
 * 以任意顺序返回这两个数字均可。
 *
 * 示例 1:
 * 输入: [1]
 * 输出: [2,3]
 *
 * 示例 2:
 * 输入: [2,3]
 * 输出: [1,4]
 * 提示：
 * nums.length <= 30000
 *
 * 链接：https://leetcode-cn.com/problems/missing-two-lcci
 *
 */
public class missing_two_lcci {
    public int[] missingTwo(int[] nums) {
        int len = nums.length;
        int num1 = len + 1, num2 = len + 2;
        for (int i = 0; i < len; i++) {
            int val = Math.abs(nums[i]);
            if (val > len) {
                if (val == num1) num1 = -1;
                if (val == num2) num2 = -1;
                continue;
            }
            nums[val - 1] = 0 - Math.abs(nums[val - 1]);
        }
        if (num1 > 0 && num2 > 0) {
            return new int[]{num1, num2};
        }

        int target1 = -1;
        int target2 = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] >= 0) {
                if (target1 < 0) {
                    target1 = i + 1;
                } else if (target2 < 0) {
                    target2 = i + 1;
                    return new int[]{target1, target2};
                }
            }
        }
        target2 = num1;
        if (num2 > 0) target2 = num2;

        return new int[]{target1, target2};
    }
}
