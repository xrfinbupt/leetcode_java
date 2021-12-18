package Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1005. K 次取反后最大化的数组和
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * <p>
 *  
 * 示例 1：
 * 输入：nums = [4,2,3], k = 1
 * 输出：5
 * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
 * <p>
 * 示例 2：
 * 输入：nums = [3,-1,0,2], k = 3
 * 输出：6
 * 解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
 * <p>
 * 示例 3：
 * 输入：nums = [2,-3,-1,5,-4], k = 2
 * 输出：13
 * 解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
 *  
 * 提示：
 * 1 <= nums.length <= 10^4
 * -100 <= nums[i] <= 100
 * 1 <= k <= 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
 *
 * @author xurongfei
 * @Date 2021/12/18
 */
public class no1005_maximize_sum_of_array_after_k_negations {
    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了30.34%的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了90.78%的用户
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue();
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }

        for (int i = 0; i < k; i++) {
            int val = queue.peek();
            if (val != 0) {
                queue.poll();
                queue.add(0 - val);
            } else {
                break;
            }
        }
        int result = 0;
        for (int iter : queue) {
            result += iter;
        }
        return result;
    }

    public static void main(String[] args) {
        no1005_maximize_sum_of_array_after_k_negations obj = new no1005_maximize_sum_of_array_after_k_negations();
        int res = obj.largestSumAfterKNegations(new int[]{1, 2, 22, -23, -9, -30, -6, -9, 1, 8, 24, 2, 21, 29, 10, -25, 18, 30, 1, 9, -8, -11, -22, -23, -17, -12, 19, 28, 19, 28}, 24);
        System.out.println(res);
    }
}
