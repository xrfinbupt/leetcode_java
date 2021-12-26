package Greedy;

/**
 * 392. Is Subsequence
 * <p>
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 * <p>
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 * <p>
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 * <p>
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *  
 * Constraints:
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * s and t consist only of lowercase English letters.
 *  
 * <p>
 * Follow up: Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 109, and you want to check one by one to see if t has its subsequence. In this scenario, how would you change your code?
 * (思路：字典树)
 * <p>
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 *
 * @author xurongfei
 * @Date 2021/12/26
 */
public class no392_is_subsequence {
    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了38.49%的用户
     */
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) {
            return true;
        }
        if( t.isEmpty() || s.length() > t.length()) {
            return false;
        }
        int j = 0;
        int len1 = s.length();
        int len2 = t.length();
        for (int i = 0; i < len1; i++) {
            char ch = s.charAt(i);
            while (j < len2 && ch != t.charAt(j)) {
                j++;
            }

            if (len1 - i > len2 - j) {
                return false;
            }
            j++;
        }
        return true;
    }
    public static void main(String []args){
        no392_is_subsequence obj = new no392_is_subsequence();
        boolean res = obj.isSubsequence("abc","ahbgdc");
        System.out.println(res);
    }
}
