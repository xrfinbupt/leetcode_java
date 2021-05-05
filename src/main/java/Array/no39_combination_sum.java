package Array;

import java.util.*;

/**
 * 39. 组合总和
 *给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class no39_combination_sum {
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 转换成子问题 分治法
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int len = candidates.length;
        for (int i = 0; i < len; i++) {
            int iter = candidates[i];
            if (iter > target) break;
            else if (iter == target) {
                List<Integer> data = new ArrayList<>();
                data.add(iter);
                result.add(data);
                break;
            }
            List<List<Integer>> datas = sub_combinationSum(candidates, target - iter, i);
            if (datas != null && datas.size() > 0) {
                for (List<Integer> data : datas) {
                    data.add(iter);
                }
                result.addAll(datas);
            }
        }

        return result;
    }

    public List<List<Integer>> sub_combinationSum(int[] candidates, int target, int index) {
        List<List<Integer>> tempResult = new ArrayList<>();
        for (int i = index; i < candidates.length; i++) {
            int iter = candidates[i];
            if (iter > target) break;
            else if (iter == target) {
                List<Integer> data = new ArrayList<>();
                data.add(iter);
                tempResult.add(data);
                break;
            }
            List<List<Integer>> datas = sub_combinationSum(candidates, target - iter, i);
            if (datas != null && datas.size() > 0) {
                int len = datas.size();
                for (int j = 0; j < len; j++) {
                    List<Integer> data = datas.get(j);
                    data.add(iter);
                }
                tempResult.addAll(datas);
            }
        }

        return tempResult;
    }

    public static void main(String args[]) {
        no39_combination_sum obj = new no39_combination_sum();
        List<List<Integer>> datas = obj.combinationSum(new int[]{2, 3, 6, 7}, 7);
        System.out.println(datas);

        obj.result.clear();
        datas = obj.combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(datas);

    }
}
