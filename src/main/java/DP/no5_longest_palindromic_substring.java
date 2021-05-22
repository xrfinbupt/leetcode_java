package DP;


/**
 * 5. 最长回文子串
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 *
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 *  
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 *
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 *
 */
public class no5_longest_palindromic_substring {
    /**
     * 方法1 字符串反转
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;

        StringBuffer str = new StringBuffer();
        int len = s.length();
        int l = 0,r = 0;
        boolean dp[][] = new boolean[len][len];
        dp[0][0] = true;
        for (int i = 1; i < len; i++) {
            char ch = s.charAt(i);
            dp[i][i] = true;
            for (int j = i-1; j >= 0; j--) {
                if (j == i - 1 && s.charAt(j) == ch) {
                    if (i - j > r - l) {
                        l = j;
                        r = i;
                    }
                    dp[j][i] = true;
                }

                if (j - 1 >= 0 && dp[j][i - 1] && s.charAt(j - 1) == ch) {
                    if (i - j + 1 > r - l) {
                        l = j - 1;
                        r = i;
                    }
                    dp[j - 1][i] = true;
                }
            }
        }

        return s.substring(l,r+1);
    }

    public static void main(String args[]){
        no5_longest_palindromic_substring obj = new no5_longest_palindromic_substring();
        String result =obj.longestPalindrome("babad");
        System.out.println(result);
        result =obj.longestPalindrome("cbbd");
        System.out.println(result);
        result =obj.longestPalindrome("b");
        System.out.println(result);
        result =obj.longestPalindrome("abccba");
        System.out.println(result);
    }
}
