package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 *
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 */
public class no47_permutations_ii {
    private List<List<Integer>> result = new ArrayList<>();
    boolean vis[];

    /**
     * 参考官方解答 https://leetcode-cn.com/problems/permutations-ii/solution/quan-pai-lie-ii-by-leetcode-solution/
     *
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<Integer> arrs = new ArrayList<>();
        int len = nums.length;
        vis = new boolean[len];

        backtracking2(nums,arrs, 0, len);

        return result;
    }

    private void backtracking2(int[] nums,List<Integer> arrs,int level,int len){
        if(level==len){
            result.add(new ArrayList<>(arrs));
            return;
        }
        for(int i=0;i<len;i++){
            // 保证在填第i个数的时候重复数字只会被填入一次即可
            // 加上 !vis[i - 1]来去重主要是通过限制一下两个相邻的重复数字的访问顺序
            //举个栗子，对于两个相同的数11，我们将其命名为1a1b, 1a表示第一个1，1b表示第二个1； 那么，不做去重的话，会有两种重复排列 1a1b, 1b1a， 我们只需要取其中任意一种排列；
            // 为了达到这个目的，限制一下1a, 1b访问顺序即可。 比如我们只取1a1b那个排列的话，只有当visit nums[i-1]之后我们才去visit nums[i]， 也就是如果!visited[i-1]的话则continue
            if(vis[i] || (i!=0 && nums[i]==nums[i-1] && !vis[i-1])) continue;

            vis[i] = true;
            arrs.add(nums[i]);
            int pos = arrs.size();
            backtracking2(nums,arrs,level+1,len);
            arrs.remove(pos-1);
            vis[i] = false;
        }
    }
}
