package lcci;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.04. 幂集
 *
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *  输入： nums = [1,2,3]
 *  输出：
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/power-set-lcci
 */
public class power_set_lcci {

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了40.69%的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        List<Integer> data = new ArrayList<>();

        sub_subsets(nums, result, 0, data);
        return result;
    }

    public void sub_subsets(int[] nums, List<List<Integer>> result, int i, List<Integer> data) {
        if (i >= nums.length) {
            result.add(new ArrayList<>(data));
            return;
        }
        sub_subsets(nums, result, i + 1, data);

        List<Integer> dataNew = new ArrayList<>(data);
        dataNew.add(nums[i]);
        sub_subsets(nums, result, i + 1, dataNew);
    }

    /**
     * 非递归实现(思路很赞) 参考网上解答
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1 << nums.length);
        if (nums == null || nums.length == 0) return result;

        result.add(new ArrayList<>());

        for (int num : nums) {
            for (int i = 0, j = result.size(); i < j; i++) {
                List<Integer> data = new ArrayList<>(result.get(i));
                data.add(num);

                result.add(data);
            }
        }

        return result;
    }

    /**
     * 非递归实现 之前78题实现过
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1 << nums.length);
        if (nums == null || nums.length == 0) return result;

        int len = nums.length;
        int sum = (1 << len) - 1;
        for (int num = 0; num <= sum; num++) {
            if (num == 0) {
                result.add(new ArrayList<>());
            } else {
                int val = num;
                List<Integer> data = new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    int temp = 1 << i;
                    if ((val & temp) > 0) {
                        data.add(nums[i]);
                        val = val - temp;
                    }

                    if (val == 0) break;
                }
                result.add(data);
            }
        }

        return result;
    }
}
