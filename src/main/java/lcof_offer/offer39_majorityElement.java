package lcof_offer;

import java.util.HashMap;

/**
 * @author xurongfei
 * @Date 2021/9/20
 * <p>
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * <p>
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * <p>
 * 输出: 2
 * 限制：
 * 1 <= 数组长度 <= 50000
 * <p>
 * 进阶：
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 * <p>
 * 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
 * <p>
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
 */
public class offer39_majorityElement {
    /**
     * 执行用时：11 ms, 在所有 Java 提交中击败了28.40%的用户
     * 内存消耗：43.7 MB, 在所有 Java 提交中击败了66.55%的用户
     */
    public int majorityElement(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int iter : nums) {
            int val = map.getOrDefault(iter, 0);
            if (val + 1 > len / 2) {
                return iter;
            }
            map.put(iter, val + 1);
        }
        return 0;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：43.9 MB, 在所有 Java 提交中击败了73.02%的用户
     */
    public int majorityElement2(int[] nums) {
        int len = nums.length;
        if (len < 1) return 0;
        if (len == 1) return nums[0];

        return majorityElement(nums, 0, len - 1);
    }

    public int majorityElement(int[] nums, int start, int end) {
        if (start >= end) return nums[end];

        int mid = (start + end) / 2;
        int left = majorityElement(nums, start, mid);
        int right = majorityElement(nums, mid + 1, end);
        if (left == right) return left;

        int leftCount = 0, rightCount = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] == left) leftCount++;
            else if (nums[i] == right) rightCount++;
        }
        if (leftCount > rightCount) return left;
        else return right;
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：44.3 MB, 在所有 Java 提交中击败了42.20%的用户
     */
    public int majorityElement3(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int count = 0;
        int candidate = 0;
        for (int i = 0; i < len; i++) {
            if (count == 0) {
                candidate = nums[i];
            }
            count += (candidate == nums[i] ? 1 : -1);
        }
        return candidate;
    }

    public static void main(String[] args) {
        offer39_majorityElement obj = new offer39_majorityElement();
        System.out.println(obj.majorityElement2(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
        System.out.println(obj.majorityElement2(new int[]{1, 2, 2}));
        System.out.println(obj.majorityElement2(new int[]{2, 2}));
        System.out.println(obj.majorityElement2(new int[]{2, 1, 2}));
        System.out.println(obj.majorityElement2(new int[]{2, 1, 2}));
        System.out.println(obj.majorityElement2(new int[]{2, 1, 2, 3, 2}));
    }
}
