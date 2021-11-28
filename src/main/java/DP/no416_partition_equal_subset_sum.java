package DP;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 416. 分割等和子集
 * <p>
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * <p>
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 *
 * @author xurongfei
 * @Date 2021/11/27
 */
public class no416_partition_equal_subset_sum {
    private boolean result = false;
    private boolean flag = false;


    /**
     * 超时了
     */
    public boolean canPartition1(int[] nums) {
        flag = false;
        result = false;

        long sum = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int iter : nums) {
            int val = countMap.getOrDefault(iter, 0);
            countMap.put(iter, val + 1);

            sum += iter;
        }
        if (sum % 2 == 1) {
            return false;
        }

        int len = countMap.size();
        int[] keys = new int[len];
        int i = 0;
        for (int key : countMap.keySet()) {
            keys[i++] = key;
        }

        long target = sum / 2;
        dfs(countMap, keys, 0, target);
        return result;
    }

    private void dfs(Map<Integer, Integer> countMap, int[] keys, int i, long leftVal) {
        if (leftVal == 0) {
            result = true;
            flag = true;
        }
        if (flag || i >= keys.length) {
            return;
        }
        int count = countMap.getOrDefault(leftVal, -1);
        if (count > 0) {
            result = true;
            flag = true;
            return;
        }

        int currVal = keys[i];
        int currCount = countMap.get(currVal);
        for (int k = 0; k <= currCount; k++) {
            if (k == 0) {
                dfs(countMap, keys, i + 1, leftVal);
            } else {
                if (currVal * k > leftVal) {
                    return;
                }
                countMap.put(currVal, currCount - k);
                dfs(countMap, keys, i + 1, leftVal - currVal * k);
                countMap.put(currVal, currCount);
            }
        }
    }

    public boolean canPartition2(int[] nums) {
        long sum = 0;
        int max = nums[0];
        for (int iter : nums) {
            sum += iter;
            max = Math.max(max, iter);
        }
        if (sum % 2 == 1 || max > (sum / 2)) {
            return false;
        }

        int len = nums.length;
        int target = (int) (sum / 2);
        boolean[][] dp = new boolean[len][target + 1];
        dp[0][nums[0]] = true;

        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < len; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[len - 1][target];
    }

    /**
     * 参考官方解答
     * https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
     * 执行用时：26 ms, 在所有 Java 提交中击败了55.79%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了66.39%的用户
     */
    public boolean canPartition(int[] nums) {
        long sum = 0;
        int max = nums[0];
        for (int iter : nums) {
            sum += iter;
            max = Math.max(max, iter);
        }
        if (sum % 2 == 1 || max > (sum / 2)) {
            return false;
        }

        int len = nums.length;
        int target = (int) (sum / 2);
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        dp[nums[0]] = true;

        for (int i = 1; i < len; i++) {
            int num = nums[i];
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        no416_partition_equal_subset_sum obj = new no416_partition_equal_subset_sum();
        int[] input = new int[]{1, 5, 11, 5};
        boolean res = obj.canPartition(input);
        System.out.println("input:" + JSON.toJSONString(input) + ",res=" + res);

        input = new int[]{1, 2, 3, 5};
        res = obj.canPartition(input);
        System.out.println("input:" + JSON.toJSONString(input) + ",res=" + res);

        input = new int[]{5, 5, 5, 5};
        res = obj.canPartition(input);
        System.out.println("input:" + JSON.toJSONString(input) + ",res=" + res);

        input = new int[]{100};
        res = obj.canPartition(input);
        System.out.println("input:" + JSON.toJSONString(input) + ",res=" + res);

        input = new int[]{5, 5, 5, 5, 10, 10, 10};
        res = obj.canPartition(input);
        System.out.println("input:" + JSON.toJSONString(input) + ",res=" + res);

        input = new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97};
        res = obj.canPartition(input);
        System.out.println("input:" + JSON.toJSONString(input) + ",res=" + res);
    }
}
