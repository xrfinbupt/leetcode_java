package String;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 *
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 */
public class no3_longest_substring_without_repeating_characters {
    public int lengthOfLongestSubstring(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;

        int max = 1;
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;
        int len = s.length();
        while (j < len) {
            char ch = s.charAt(j);
            if (set.contains(ch)) {
                while (i < j) {
                    char ch2 = s.charAt(i);
                    set.remove(ch2);
                    i++;
                    if (ch2 == ch) break;
                }
            }
            set.add(ch);
            if (set.size() > max) max = set.size();
            j++;
        }
        return max;
    }
    public static void main(String args[]){
        no3_longest_substring_without_repeating_characters obj = new no3_longest_substring_without_repeating_characters();
        System.out.println("abcabcbb="+obj.lengthOfLongestSubstring("abcabcbb"));
        System.out.println("bbbbb="+obj.lengthOfLongestSubstring("bbbbb"));
        System.out.println("pwwkew="+obj.lengthOfLongestSubstring("pwwkew"));
        System.out.println("="+obj.lengthOfLongestSubstring(""));
        System.out.println("abcd="+obj.lengthOfLongestSubstring("abcd"));
        System.out.println(" abcab cbb ="+obj.lengthOfLongestSubstring(" abcab cbb "));
    }
}
