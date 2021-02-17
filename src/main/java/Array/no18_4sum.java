package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 *
 * https://leetcode-cn.com/problems/4sum
 */
public class no18_4sum {

    /**
     * 遇到一个问题：两次提交代码一样 执行时间不一样 可能后台服务器负载不同 影响了结果
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;
        Arrays.sort(nums);

        int len = nums.length;
        if (nums[0] + nums[1] + nums[2] + nums[3] > target) return result;
        if (nums[len - 4] + nums[len - 3] + nums[len - 2] + nums[len - 1] < target) return result;

        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[len - 3] + nums[len - 2] + nums[len - 1] < target) continue;


            for (int ii = i + 1; ii < len - 2; ii++) {
                if (ii > i + 1 && nums[ii] == nums[ii - 1]) continue;
                if (nums[i] + nums[ii] + nums[ii + 1] + nums[ii + 2] > target) break;
                if (nums[i] + nums[ii] + nums[len - 2] + nums[len - 1] < target) continue;


                int j = ii + 1;
                int k = len - 1;
                while (j < k) {
                    int val = nums[i] + nums[ii] + nums[j] + nums[k];
                    if (val == target) {
                        List<Integer> tempResult = new ArrayList<>();
                        tempResult.add(nums[i]);
                        tempResult.add(nums[ii]);
                        tempResult.add(nums[j]);
                        tempResult.add(nums[k]);
                        result.add(tempResult);
                        while (j + 1 <= len - 1 && nums[j] == nums[j + 1]) {
                            j++;
                        }
                        while (k - 1 > j && nums[k] == nums[k - 1]) {
                            k--;
                        }
                        j++;
                        k--;
                    } else if (val - target > 0) {
                        if (j + 1 <= k && nums[i] + nums[ii] + nums[j] + nums[j + 1] > target) break;

                        k--;
                    } else if (val - target < 0) {
                        if (j <= k - 1 && nums[i] + nums[ii] + nums[k - 1] + nums[k] < target) break;

                        j++;
                    }
                }
            }
        }

        return result;
    }

    /**
     * 更加通用的方法：n数之和 （前面已经计算了2数之和，3数之和，4数之和）
     *
     * 参考：https://leetcode-cn.com/problems/3sum/solution/yi-ge-fang-fa-tuan-mie-by-labuladong/
     *
     * @param nums
     * @param n
     * @param start
     * @param target
     */
    public static List<List<Integer>> nSum(int[] nums,int n, int start,int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null || n < 2 || nums.length < n) return result;

        int len = nums.length;
        if(n == 2){
            int l = start,r=len-1;
            while(l < r){
                int val = nums[l] + nums[r];
                if(val == target){
                    List<Integer> tempResult = new ArrayList<>();
                    tempResult.add(nums[l]);
                    tempResult.add(nums[r]);
                    result.add(tempResult);
                    while(l+1<=r && nums[l] == nums[l+1]) l++;
                    while(l<=r-1 && nums[r] == nums[r-1]) r--;
                    l++;
                    r--;
                }else if(val > target){
                    r--;
                }else if(val < target){
                    l++;
                }
            }
        }else{
            for(int i=start;i<=len-n;i++){
                if(i>start && nums[i] == nums[i-1]) continue;

                List<List<Integer>> tempResult = nSum(nums,n-1,i+1,target - nums[i]);
                if(tempResult.size()>0) {
                    for (List<Integer> iter : tempResult) {
                        iter.add(nums[i]);
                        result.add(iter);
                    }
                }
            }
        }
        return result;
    }
    public List<List<Integer>> fourSumN(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;

        Arrays.sort(nums);
        result = nSum(nums,4,0,target);

        return result;
    }
    public static void main(String args[]){
        no18_4sum obj = new no18_4sum();
        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        List<List<Integer>> result = obj.fourSum(nums, 0);
        System.out.println(result);
        System.out.println("---------");

        result = obj.fourSumN(nums,0);
        System.out.println(result);
        System.out.println("---------");

        nums = new int[]{1,-2,-5,-4,-3,3,3,5};
        result = obj.fourSumN(nums,-11);
        System.out.println(result);
        System.out.println("---------");
    }
}
