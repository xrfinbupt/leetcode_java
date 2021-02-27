package Array;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 * 链接：https://leetcode-cn.com/problems/subsets
 */
public class no78_subsets {
    private List<Integer> temp = new ArrayList<>();
    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 方法1
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        int len = nums.length;
        List<Integer> data = new ArrayList<>();
        recursive(nums,0,len,data);
        return result;
    }
    private void recursive(int []nums, int start, int len, List<Integer> data){
        if(start>len) {
            return;
        } else if(start== len){
            result.add(new ArrayList<>(data));
            return;
        }

        data.add(nums[start]);
        recursive(nums, start + 1, len, data);

        data.remove(data.size()-1);
        recursive(nums, start + 1, len, data);
    }

    /**
     * 方法2 回溯法 画图后很清晰temp空间如何利用的
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        int len = nums.length;
        dfs(nums,0,len);
        return result;
    }
    private void dfs(int[] nums,int level,int len){
        if(level == len){
            result.add(new ArrayList<>(temp));
            return;
        }
        temp.add(nums[level]);
        dfs(nums,level+1,len);

        temp.remove(temp.size()-1);
        dfs(nums,level+1,len);
    }

    /**
     * 方法3 迭代法实现子集枚举 参考官方解答
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        int count = 1 << len;//len位，二进制组合个数
        int mask = count -1;
        List<Integer> temp = new ArrayList<>();
        for(int i=0;i<count;i++){
            if(i==0){
                result.add(new ArrayList<>());
            }else{
                int val = i;
                for(int j=0;j<len;j++){
                    if((val & (1<<j)) > 0){
                        temp.add(nums[j]);
                        val = val - (1<<j);
                    }
                    if((val & mask)==0){
                        result.add(new ArrayList<>(temp));
                        temp.clear();
                        break;
                    }
                }
            }
        }

        return result;
    }
    public static void main(String args[]) {
        no78_subsets obj = new no78_subsets();
        int data[] = new int[]{1,2,3,4};
        List<List<Integer>> result = obj.subsets1(data);
        System.out.println(result);
        System.out.println("-----------");
    }
}
