package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. 汇总区间
 * 给定一个无重复元素的有序整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 *  
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 *
 * 示例 2：
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 * 示例 3：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：["-1"]
 *
 * 示例 5：
 * 输入：nums = [0]
 * 输出：["0"]
 *  
 * 提示：
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums 中的所有值都 互不相同
 * nums 按升序排列
 *
 * 链接：https://leetcode-cn.com/problems/summary-ranges
 */
public class no228_summary_ranges {
    /**
     * 想复杂了 有序的数组 且 不重复
     * @param nums
     * @return
     */
    public List<String> summaryRanges1(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length < 1) return result;
        if(nums.length == 1) {
            result.add(nums[0]+"");
            return result;
        }
        int l=-1,r=-1;
        for(int i=0;i<nums.length;i++){
            int iter = nums[i];
            if (l < 0 || r < 0) {
                l = i;
                r = i;
            } else if (iter == nums[r] || iter == nums[r] + 1) {
                r = i;
            } else {
                if (nums[r] == nums[l]) {
                    result.add(nums[l] + "");
                } else {
                    result.add(nums[l] + "->" + nums[r]);
                }
                l = r = i;
            }


            if (iter == Integer.MAX_VALUE) {
                if (nums[r] == iter || iter == nums[r] + 1) {
                    if (nums[l] == nums[r]) {
                        result.add(iter + "");
                    } else {
                        result.add(nums[l] + "->" + nums[r]);
                    }
                } else {
                    result.add(nums[l] + "->" + nums[r]);
                    result.add(iter + "");
                }
                l = r = -1;
            }
        }

        if(l >=0) {
            if (nums[r] == nums[l]) {
                result.add(nums[l] + "");
            } else {
                result.add(nums[l] + "->" + nums[r]);
            }
        }

        return result;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if(nums == null || nums.length < 1) return result;
        int n = nums.length;
        int i = 0;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) i++;
            int high = i - 1;
            StringBuilder sb = new StringBuilder(Integer.toString(nums[low]));
            if (low < high) {
                sb.append("->");
                sb.append(nums[high]);
            }
            result.add(sb.toString());
        }
        return result;
    }
    public static void main(String args[]) {
        no228_summary_ranges obj = new no228_summary_ranges();
        List<String> result = obj.summaryRanges(new int[]{0, 1, 2, 3, 3,5});
        System.out.println(result);

        result = obj.summaryRanges(new int[]{0,1,2,4,5,7});
        System.out.println(result);

        result = obj.summaryRanges(new int[]{0,1,2,3,5,2147483647,-2147483648});
        System.out.println(result);
    }
}
