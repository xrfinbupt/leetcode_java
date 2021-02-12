package Array;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
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
                int k = j+1;
                int kk = len -1;
                while(k<=kk){
                    int mid = k + (kk -k)/2;
                    int val = nums[i]+nums[j]+nums[mid];
                    if (val== 0) {
                        List<Integer> tempResult = new ArrayList<>();
                        tempResult.add(nums[i]);
                        tempResult.add(nums[j]);
                        tempResult.add(nums[mid]);
                        result.add(tempResult);
                        break;
                    }else if(val > 0){
                        kk = mid -1;
                    }else if(val < 0){
                        k = mid + 1;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String args[]) {
        no15_3sum obj = new no15_3sum();
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> result = obj.threeSum(nums);
        System.out.println(result);
        System.out.println("---------");
        nums = new int[]{-2,0,0,2,2};
        result = obj.threeSum(nums);
        System.out.println(result);
        System.out.println("---------");
    }
}
