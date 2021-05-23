package Array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 */
public class no40_combination_sum_ii {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        sub_combinationSum(candidates, 0, new ArrayList<>(), target);

        return result;
    }

    public void sub_combinationSum(int[] candidates, int start, List<Integer> temp, int target) {
        int len = candidates.length;
        if (start >= len) return;

        for (int i = start; i < len; i++) {
            int val = candidates[i];
            if(i>start && candidates[i-1]==val) continue;
            if (val > target) break;

            temp.add(val);
            if (val == target) {
                result.add(new ArrayList<>(temp));
                break;
            }

            sub_combinationSum(candidates, i + 1, new ArrayList<>(temp), target - val);
            temp.remove(temp.size() - 1);
        }
    }
}
