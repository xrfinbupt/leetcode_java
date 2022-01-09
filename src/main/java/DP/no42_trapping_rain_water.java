package DP;

/**
 * 42. 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 * 提示：
 * n == height.length
 * 0 <= n <= 3 * 10^4
 * 0 <= height[i] <= 10^5
 *
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 */
public class no42_trapping_rain_water {
    /**
     * 动态规划 双指针（left_dp,right_dp）
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int len = height.length;
        if(len <= 2){
            return 0;
        }
        int left[] = new int[len];
        int right[] = new int[len];
        for(int i=0;i<len;i++){
            left[i] = Math.max(i-1>=0?left[i-1]:0,height[i]);
            right[len-1-i] = Math.max(i-1>=0?right[len-i]:0,height[len-1-i]);
        }

        int sum = 0;
        for(int i=1;i<len-1;i++){
            int min = Math.min(left[i-1],right[i+1]);
            if(height[i]<min) sum += min - height[i];
        }

        return sum;
    }
    public static void main(String args[]){
        no42_trapping_rain_water obj = new no42_trapping_rain_water();
        int sum = obj.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
        System.out.println(sum);

        sum = obj.trap(new int[]{4,2,0,3,2,5});
        System.out.println(sum);

        sum = obj.trap(new int[]{4,2});
        System.out.println(sum);
    }
}
