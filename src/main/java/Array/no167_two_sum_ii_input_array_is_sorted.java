package Array;

/**
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 *  
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 *
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 *
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 */
public class no167_two_sum_ii_input_array_is_sorted {
    /**
     * 双指针方法
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null || numbers.length < 2) return null;
        int l=0,r=numbers.length-1;
        while(l<r){
            int sum = numbers[l] + numbers[r];
            if(sum == target){
                return new int[]{l+1,r+1};
            }else if(sum > target){
                r--;
            }else{
                l++;
            }
        }
        return null;
    }

    /**
     * 双指针+二分查找
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum_1(int[] numbers, int target) {
        if(numbers == null || numbers.length < 2) return null;

        int l=0,r=numbers.length-1;
        while(l<r){
            int mid = l + (r-l)/2;
            // 这个地方 mid 可能等于l
            if(numbers[l]+numbers[mid]>target){
                r = mid -1;
            }else if(numbers[mid]+numbers[r]<target){
                l = mid+1;
            }else if(numbers[l]+numbers[r]>target){
                r--;
            }else if(numbers[l]+numbers[r]<target){
                l++;
            }else{
                return new int[]{l+1,r+1};
            }
        }
        return null;
    }
}
