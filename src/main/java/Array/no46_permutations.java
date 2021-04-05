package Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/permutations
 *
 */
public class no46_permutations {
    List<List<Integer>> result = new ArrayList<>();

    /**
     * 回溯法
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null || nums.length == 0) return result;

        int len = nums.length;
        List<Integer> arrs = new ArrayList<Integer>();
        for(int iter:nums) arrs.add(iter);

        List<Integer> temp = new ArrayList<Integer>();
        backtracking(arrs,temp,0,len);

        return result;
    }
    private void backtracking(List<Integer> arrs,List<Integer> temp,int deep,int len){
        if(temp.size() >= len){
            result.add(new ArrayList<>(temp));
            return;
        }
        for(Integer iter:arrs){
            if(temp.contains(iter)) continue;
            temp.add(iter);
            backtracking(arrs,temp,deep+1,len);
            temp.remove((Object)iter);
        }
    }

    /**
     * 参考官方解答 (思路很妙)
     * 执行用时：1 ms, 在所有 Java 提交中击败了96.75%的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        if (nums == null || nums.length == 0) return result;

        List<Integer> arrs = new ArrayList<Integer>();
        int len = nums.length;
        for(int iter:nums) arrs.add(iter);
        backtracking2(arrs,0,len);

        return result;
    }

    private void backtracking2(List<Integer> arrs,int start,int len){
        if(start==len){
            result.add(new ArrayList<>(arrs));
            return;
        }
        for(int i=start;i<len;i++){
            Collections.swap(arrs,i,start);
            backtracking2(arrs,start+1,len);
            Collections.swap(arrs,i,start);
        }
    }
}
