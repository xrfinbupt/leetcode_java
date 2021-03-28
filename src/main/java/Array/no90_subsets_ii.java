package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 *
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
     * 方法1 好理解一点（每一位数 要不参与 要不不参与）
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

        // 相同的数 当作一个整体
        int end = start;
        while(end< len && nums[end] == nums[start]) end++;

        dfs(nums,end,len,new ArrayList<>(data));
        for(int i=start;i<end;i++){
            data.add(nums[i]);
            dfs(nums,end,len,new ArrayList<>(data));
        }
    }

    /**
     * 方法2 参考官方解法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        if(nums == null || nums.length < 1) return result;

        Arrays.sort(nums);
        int len = nums.length;
        List<Integer> data = new ArrayList<>();
        dfs2(nums,0,len,data);

        return result;
    }
    private void dfs2(int nums[],int start,int len,List<Integer> data){
        result.add(new ArrayList<>(data));

        for(int i = start;i<len;i++){
            if(i>start && nums[i] == nums[i-1]) continue;

            data.add(nums[i]);
            dfs2(nums,i+1,len,data);
            data.remove(data.size()-1);
        }

    }

    public static void main(String args[]) {
        no90_subsets_ii obj = new no90_subsets_ii();
        int data[] = new int[]{1,2,2,3};
        List<List<Integer>> result = obj.subsetsWithDup2(data);
        System.out.println(result);
        System.out.println("-----------");
    }
}
