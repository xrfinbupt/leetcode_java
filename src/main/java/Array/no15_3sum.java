package Array;

import java.util.*;

import static Array.no18_4sum.nSum;

/**
 * 15. 三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 *
 * 链接：https://leetcode-cn.com/problems/3sum
 */
public class no15_3sum {
    /**
     * 暴力法 + 排序 + 二分
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;
        Arrays.sort(nums);

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k = j + 1;
                int kk = len - 1;
                while (k <= kk) {
                    int mid = k + (kk - k) / 2;
                    int val = nums[i] + nums[j] + nums[mid];
                    if (val == 0) {
                        List<Integer> tempResult = new ArrayList<>();
                        tempResult.add(nums[i]);
                        tempResult.add(nums[j]);
                        tempResult.add(nums[mid]);
                        result.add(tempResult);
                        break;
                    } else if (val > 0) {
                        kk = mid - 1;
                    } else if (val < 0) {
                        k = mid + 1;
                    }
                }
            }
        }

        return result;
    }

    /**
     * 排序 + 双指针
     * 一些小优化 击败了79.57%的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;
        Arrays.sort(nums);

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if( nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int j = i + 1;
            int k = len - 1;
            while (j < k) {
                int val2 = nums[j];
                int val3 = nums[k];
                int val = nums[i] + val2+ val3;
                if (val == 0) {
                    List<Integer> tempResult = new ArrayList<>();
                    tempResult.add(nums[i]);
                    tempResult.add(nums[j]);
                    tempResult.add(nums[k]);
                    result.add(tempResult);
                    while (j + 1 <= len - 1  && val2 == nums[j + 1]) {
                        j++;
                    }
                    while (k - 1 > j  && val3 == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (val > 0) {
                    k--;
                } else if (val < 0) {
                    j++;
                }
            }
        }

        return result;
    }

    /**
     * 排序+hashmap
     * 来自 https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-by-leetcode-solution/
     * 比较耗时
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum3(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }

        for (int i = 0; i < nums.length - 2; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                int tmp = -nums[j] - nums[i];
                if (tmp < nums[j]) {
                    continue;
                }
                if (map.containsKey(tmp)) {
                    //三个元素相同，都是0
                    if (nums[i] == 0 && nums[j] == 0 && map.get(0) < 3) {
                        continue;
                    }
                    //两个元素相同,如 -2 1 1
                    if (nums[j] == tmp && map.get(tmp) < 2) {
                        continue;
                    }
                    //正常情况
                    if (!res.isEmpty()
                            && res.get(res.size() - 1).get(1) == nums[j]
                            && res.get(res.size() - 1).get(0) == nums[i]) {
                        continue;
                    }
                    List<Integer> array = new ArrayList<Integer>();
                    array.add(nums[i]);
                    array.add(nums[j]);
                    array.add(tmp);
                    res.add(array);
                }
            }
        }
        return res;
    }
    public List<List<Integer>> threeSumN(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);
        result = nSum(nums,3,0,0);

        return result;
    }

    public static void main(String args[]) {
        no15_3sum obj = new no15_3sum();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = obj.threeSumN(nums);
        System.out.println(result);
        System.out.println("---------");

        nums = new int[]{-2, 0, 0, 2, 2};
        result = obj.threeSumN(nums);
        System.out.println(result);
        System.out.println("---------");

        nums = new int[]{-2, 0, 1, 1, 2};
        result = obj.threeSumN(nums);
        System.out.println(result);
        System.out.println("---------");
    }
}
