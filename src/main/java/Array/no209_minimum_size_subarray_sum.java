package Array;

import java.util.Arrays;
import java.util.List;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *  
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 */
public class no209_minimum_size_subarray_sum {
    /**
     * 方法：双指针
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0;
        int min = nums[0];
        int minLen = Integer.MAX_VALUE;
        int sum = nums[0];
        if (sum == target) return 1;

        int len = nums.length;
        while (r < len) {
            if(sum>=target){
                min = Math.min(min,sum);
                minLen = Math.min(minLen,r-l+1);
                l++;
                sum = sum - nums[l-1];
            }else{
                r++;
                if(r >= len) break;
                sum = sum + nums[r];
            }

            if(l>r) r++;
        }
        if(minLen == Integer.MAX_VALUE) return 0;

        return minLen;
    }

    /**
     * 官方双指针解答
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 方法二：前缀和 + 二分查找
     * 前缀后这个是关键 sums[i] 表示从nums[0] 到 nums[i−1] 的元素和
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int len = nums.length;
        int result = Integer.MAX_VALUE;
        int sum[] = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int i = 0; i < len; i++) {
            int target2 = target + sum[i];
            int pos = Arrays.binarySearch(sum, target2);
            if (pos < 0) {
                pos = 0 - pos - 1;
            }
            if (pos <= len) {
                result = Math.min(result, pos - i);
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String args[]) {
        no209_minimum_size_subarray_sum obj = new no209_minimum_size_subarray_sum();
        int data[] = new int[]{2,3,1,2,4,3};
        int result = obj.minSubArrayLen2(7,data);
        System.out.println(result);
        System.out.println("-----------");

        data = new int[]{1,4,4};
        result = obj.minSubArrayLen2(4,data);
        System.out.println(result);
        System.out.println("-----------");

        data = new int[]{1,1,1,1,1,1,1,1};
        result = obj.minSubArrayLen2(9,data);
        System.out.println(result);
        System.out.println("-----------");

        data = new int[]{1,1,1,1,1,1,1,1};
        result = obj.minSubArrayLen2(5,data);
        System.out.println(result);
        System.out.println("-----------");
    }
}
