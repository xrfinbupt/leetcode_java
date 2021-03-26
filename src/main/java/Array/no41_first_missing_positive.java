package Array;

/**
 * 41. 缺失的第一个正数
 *
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 *
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *  
 * 提示：
 * 0 <= nums.length <= 300
 * -231 <= nums[i] <= 231 - 1
 *
 * https://leetcode-cn.com/problems/first-missing-positive
 */
public class no41_first_missing_positive {
    /**
     * 自己实现的一种方法
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive_method1(int[] nums) {
        if (nums == null || nums.length == 0) return 1;

        int len = nums.length;
        // 把负数以及大于数据长度的元素初始化成0 后续这个地方可以补任意的值
        for (int i = 1; i <= len; i++) {
            if (nums[i - 1] < 0 || nums[i - 1] > len) {
                nums[i - 1] = 0;
            }
        }

        boolean flag = false;
        for (int iter : nums) {
            if (iter < 0) {
                iter = 0 - iter;
            }

            if (iter > 0) {
                if (nums[iter - 1] > 0) {
                    nums[iter - 1] *= -1;
                    flag = true;
                } else if (nums[iter - 1] == 0) {
                    // 这个地方补当前iter 负值
                    nums[iter - 1] = 0 - iter;
                    flag = true;
                }
            }
        }

        for (int i = 1; i <= len; i++) {
            int val = nums[i - 1];
            if (val >= 0) {
                return i;
            }
        }
        if (!flag) return 1;
        return len + 1;
    }

    /**
     * 方法 哈希表
     * 打标记
     * 比我思维想的周到 佩服
     * https://leetcode-cn.com/problems/first-missing-positive/solution/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive_method2(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1; // 大于n的数即可
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) { // 这一步很 妙，大于n的数不管
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 置换法
     * 比我思维想的周到 佩服
     * https://leetcode-cn.com/problems/first-missing-positive/solution/que-shi-de-di-yi-ge-zheng-shu-by-leetcode-solution/
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] != i) {
                return i;
            }
        }
        return n + 1;
    }

    public static void main(String args[]) {
        no41_first_missing_positive obj = new no41_first_missing_positive();
        int result = obj.firstMissingPositive(new int[]{1, 2});
        System.out.println("array[1,2] result:" + result);

        result = obj.firstMissingPositive(new int[]{1, -2});
        System.out.println("array[1,-2] result:" + result);

        result = obj.firstMissingPositive(new int[]{2, 1});
        System.out.println("array[2,1] result:" + result);

        result = obj.firstMissingPositive(new int[]{3, 4, -1, 1});
        System.out.println("array[3,4,-1,1] result:" + result);

        result = obj.firstMissingPositive(new int[]{1, 2, 0});
        System.out.println("array[1,2,0] result:" + result);

        result = obj.firstMissingPositive(new int[]{1});
        System.out.println("array[1] result:" + result);

        result = obj.firstMissingPositive(new int[]{-1, -2});
        System.out.println("array[-1,-2] result:" + result);

        result = obj.firstMissingPositive(new int[]{7, 8, 9, 11, 12});
        System.out.println("array[7,8,9,11,12] result:" + result);
    }
}
