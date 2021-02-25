package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 输入: [1,2,2,3,3]
 * 输出：
 *
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 */
public class no90_subsets_ii {
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums == null || nums.length < 1) return result;

        Arrays.sort(nums);
        int len = nums.length;
        List<Integer> data = new ArrayList<>();
        dfs(nums,0,len,data);

        return result;
    }
    private void dfs(int[] nums,int start,int len,List<Integer> data){
        if(start >= len){
            result.add(new ArrayList<>(data));
            return;
        }

        int end = start;
        while(end< len && nums[end] == nums[start]) end++;

        dfs(nums,end,len,new ArrayList<>(data));
        for(int i=start;i<end;i++){
            data.add(nums[i]);
            dfs(nums,end,len,new ArrayList<>(data));
        }
    }

    public static void main(String args[]) {
        no90_subsets_ii obj = new no90_subsets_ii();
        int data[] = new int[]{1,2,2};
        List<List<Integer>> result = obj.subsetsWithDup(data);
        System.out.println(result);
        System.out.println("-----------");
    }
}
