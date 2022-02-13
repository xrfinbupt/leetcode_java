package DP;

import com.alibaba.fastjson.JSON;

/**
 * 474. 一和零
 * <p>
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 * <p>
 * 示例 2：
 * 输入：strs = ["10", "0", "1"], m = 1, n = 1
 * 输出：2
 * 解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
 *  
 * <p>
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 * <p>
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 *
 * @author xurongfei
 * @Date 2022/2/13
 */
public class no474_ones_and_zeroes {
    /**
     * 01背包
     *
     * 执行用时：46 ms, 在所有 Java 提交中击败了30.29%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了31.01%的用户
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < len; i++) {
            int oneCount = 0;
            int zeroCount = 0;
            int len1 = strs[i].length();
            for (int z = 0; z < len1; z++) {
                if (strs[i].charAt(z) == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }

            int[][] temp_dp = new int[m + 1][n + 1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    temp_dp[j][k] = dp[j][k];
                    if (zeroCount <= j && oneCount <= k) {
                        temp_dp[j][k] = Math.max(dp[j][k], dp[j - zeroCount][k - oneCount] + 1);
                    }
                }
            }
            dp = temp_dp;
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        no474_ones_and_zeroes obj = new no474_ones_and_zeroes();
        String[] input = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        int res = obj.findMaxForm(input, m, n);
        System.out.println("input=" + JSON.toJSONString(input) + ",m=" + m + ",n=" + n + ",result=" + res);

        input = new String[]{"10", "0001", "111001", "1", "0"};
        m = 7;
        n = 7;
        res = obj.findMaxForm(input, m, n);
        System.out.println("input=" + JSON.toJSONString(input) + ",m=" + m + ",n=" + n + ",result=" + res);

        input = new String[]{"10", "0", "1"};
        m = 1;
        n = 1;
        res = obj.findMaxForm(input, m, n);
        System.out.println("input=" + JSON.toJSONString(input) + ",m=" + m + ",n=" + n + ",result=" + res);
    }
}
