package lcci;

import java.util.HashMap;

/**
 * 面试题 01.02. 判定是否互为字符重排
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 *
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 *
 * 说明：
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 * 链接：https://leetcode-cn.com/problems/check-permutation-lcci
 *
 */
public class check_permutation_lcci {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (char ch : s1.toCharArray()) {
            Integer res = map.getOrDefault(ch, new Integer(0));
            map.put(ch, res + 1);
        }

        for (char ch : s2.toCharArray()) {
            Integer res = map.get(ch);
            if (res == null || res < 1) return false;

            map.put(ch, res - 1);
        }
        return true;
    }
}
