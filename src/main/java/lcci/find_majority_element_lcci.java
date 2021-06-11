package lcci;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.10. 主要元素
 *
 * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 * 示例 1：
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 *  
 * 示例 2：
 * 输入：[3,2]
 * 输出：-1
 *  
 * 示例 3：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 * 说明：
 * 你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
 *
 * 链接：https://leetcode-cn.com/problems/find-majority-element-lcci
 */
public class find_majority_element_lcci {
    /**
     * 执行用时：18 ms, 在所有 Java 提交中击败了14.85%的用户
     * 内存消耗：43.6 MB, 在所有 Java 提交中击败了31.65%的用户
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length < 1) return -1;

        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for( int i=0;i<nums.length;i++){
            int val = map.getOrDefault(nums[i],0);
            if(val+1>len/2){
                return nums[i];
            }
            map.put(nums[i],val+1);
        }
        return -1;
    }

    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了53.97%的用户
     * 内存消耗：43.1 MB, 在所有 Java 提交中击败了39.21%的用户
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        if(nums == null || nums.length < 1) return -1;

        int result[] = sub_majorityElement2(nums,0,nums.length-1);
        if(result!=null) return result[0];

        return -1;
    }

    public int[] sub_majorityElement2(int[] nums, int s, int e) {
        if (s == e) return new int[]{nums[s],1};

        int mid = s + (e - s) / 2;
        int[] left = sub_majorityElement2(nums, s, mid);
        int[] right = sub_majorityElement2(nums, mid + 1, e);
        if (left != null && right != null) {
            if (left[0] == right[0]) {
                left[1] += right[1];
                return left;
            }
        }
        if (left != null) {
            int count = left[1];
            for (int i = mid + 1; i <= e; i++) {
                if (nums[i] == left[0]) count++;
            }
            left[1] = count;
            if (count > (e - s + 1) / 2) return left;
        }

        if (right != null) {
            int count = right[1];
            for (int i = s; i <= mid; i++) {
                if (nums[i] == right[0]) count++;
            }
            right[1] = count;
            if (count > (e - s + 1) / 2) return right;
        }
        return null;
    }

    /**
     * 参考方法
     * https://leetcode-cn.com/problems/find-majority-element-lcci/solution/java-fu-er-mo-si-tou-piao-fa-zhu-xing-zh-0y6d/
     *
     * @param nums
     * @return
     */
    public int majorityElement3(int[] nums) {
        if (nums == null || nums.length < 1) return -1;

        int count = 0;
        int preVal = -1;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                count = 1;
                preVal = nums[i];
            } else if (preVal == nums[i]) count++;
            else {
                count--;
            }
        }

        if (count > 0) {
            int cnt = 0;
            for (int iter : nums) if (preVal == iter) cnt++;
            if (cnt > nums.length / 2) return preVal;
        }

        return -1;
    }

    public static void main(String args[]){
        find_majority_element_lcci obj = new find_majority_element_lcci();
        System.out.println(obj.majorityElement3(new int[]{1,2,5,9,5,9,5,5,5}));
        System.out.println(obj.majorityElement3(new int[]{3,2}));
        System.out.println(obj.majorityElement3(new int[]{2,2,1,1,1,2,2}));
    }
}
