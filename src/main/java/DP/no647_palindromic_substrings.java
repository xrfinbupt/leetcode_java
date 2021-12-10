package DP;

/**
 * 647. 回文子串
 * <p>
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * 示例 1：
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * <p>
 * 示例 2：
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 *
 * @author xurongfei
 * @Date 2021/12/10
 */
public class no647_palindromic_substrings {
    /**
     * 执行用时：11 ms, 在所有 Java 提交中击败了26.79%的用户
     * 内存消耗：38.1 MB, 在所有 Java 提交中击败了48.64%的用户
     */
    public int countSubstrings(String s) {
        int len = s.length();
        int result = 0;
        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = true;
                    result++;
                } else if (i + 1 == j) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        result++;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        result++;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        no647_palindromic_substrings obj = new no647_palindromic_substrings();
        int res = obj.countSubstrings("abc");
        System.out.println(res);

        res = obj.countSubstrings("aaa");
        System.out.println(res);
    }
}
