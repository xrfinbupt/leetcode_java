package DP;

/**
 * 1014. 最佳观光组合
 *
 * 给你一个正整数数组 values，其中 values[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的 距离 为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j ，也就是景点的评分之和 减去 它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 * 示例 1：
 * 输入：values = [8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 *
 * 示例 2：
 * 输入：values = [1,2]
 * 输出：2
 *
 * 提示：
 * 2 <= values.length <= 5 * 10^4
 * 1 <= values[i] <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
 */
public class no1014_best_sightseeing_pair {
    /**
     * 超出时间限制了
     * @param values
     * @return
     */
    public int maxScoreSightseeingPair(int[] values) {
        int sum = 0;
        int len = values.length;
        for(int i=0;i<len-1;i++){
            for(int j=i+1;j<len;j++){
                int val = values[i] + values[j] + i - j;
                if(val>sum) sum = val;
            }
        }
        return sum;
    }

    /**
     * https://leetcode-cn.com/problems/best-sightseeing-pair/solution/1014-zui-jia-guan-guang-zu-he-dpsi-xiang-eted/
     * 得到状态转化方程：
     * dp[i] = Math.max(dp[i-1],i+values[i]) (统计当前最大值)
     * 滚动数组中的变量：
     * max = Math.max(max,dp[i-1] + values[i] - i)
     *
     * 一对景点（i < j）组成的观光组合的得分为 values[i] + values[j] + i - j  ====  values[i] + i + values[j] - j
     * @param values
     * @return
     */
    public int maxScoreSightseeingPair2(int[] values) {
        int ans = 0,max = values[0]+0;
        for(int i=1;i<values.length;i++){
            ans = Math.max(ans,max+values[i]-i);
            max = Math.max(max,values[i]+i);
        }
        return ans;
    }
}
