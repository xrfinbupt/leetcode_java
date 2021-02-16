package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 */
public class no16_3sum_closest {
    /**
     * 排序+双指针
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest1(int[] nums, int target) {
        int result = Integer.MAX_VALUE;
        int diff = Integer.MAX_VALUE;
        Arrays.sort(nums);

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if(i>0 && nums[i] == nums[i-1]) continue;

            int j = i + 1;
            int k = len - 1;
            while (j < k) {
                int temp = nums[i] + nums[j] + nums[k];
                if (temp == target) return target;

                int diff1 = Math.abs(temp - target);
                if (diff1 < diff) {
                    diff = diff1;
                    result = temp;
                }

                if (temp - target > 0) {
                    k--;
                } else if (temp - target < 0) {
                    j++;
                }
            }
        }

        return result;
    }

    /**
     * 排序+双指针 (优化)
     * @param nums
     * @param target
     * @return
     */
    public int threeSumCloses2(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length-2;i++){
            int left = i+1;
            int right = nums.length - 1;
            while(left != right){
                int min = nums[i] + nums[left] + nums[left + 1];
                if(target < min){
                    if(Math.abs(result - target) > Math.abs(min - target))
                        result = min;
                    break;
                }
                int max = nums[i] + nums[right] + nums[right - 1];
                if(target > max){
                    if(Math.abs(result - target) > Math.abs(max - target))
                        result = max;
                    break;
                }
                int sum = nums[i] + nums[left] + nums[right];
                // 判断三数之和是否等于target
                if(sum == target)
                    return sum;
                if(Math.abs(sum - target) < Math.abs(result - target))
                    result = sum;
                if(sum > target){
                    right--;
                    while(left != right && nums[right] == nums[right+1])
                        right--;
                }
                else{
                    left++;
                    while(left != right && nums[left] == nums[left-1])
                        left++;
                }
            }
            while(i<nums.length-2 && nums[i] == nums[i+1])
                i++;
        }
        return result;
    }

    public static void main(String args[]) {
        no16_3sum_closest obj = new no16_3sum_closest();
        int[] nums = new int[]{-1,2,1,-4};
        int result = obj.threeSumCloses2(nums,1);
        System.out.println("2:"+result);
        System.out.println("---------");

        nums = new int[]{4,0,5,-5,3,3,0,-4,-5};
        result = obj.threeSumCloses2(nums,-2);
        System.out.println("-2:"+result);
        System.out.println("---------");

        nums = new int[]{-55,-24,-18,-11,-7,-3,4,5,6,9,11,23,33};
        result = obj.threeSumCloses2(nums,0);
        System.out.println("0:"+result);
        System.out.println("--------");
    }
}
