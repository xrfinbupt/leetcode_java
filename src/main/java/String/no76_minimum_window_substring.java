package String;

import java.nio.charset.CharacterCodingException;
import java.util.*;

/**
 * 76. 最小覆盖子串（困难）
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 * 提示：
 * 1 <= s.length, t.length <= 10^5
 * s 和 t 由英文字母组成
 *  
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 *
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 */
public class no76_minimum_window_substring {
    /**
     * 太打击人了吧
     * 执行用时：257 ms, 在所有 Java 提交中击败了5.04%的用户
     * 执行用时：76 ms, 在所有 Java 提交中击败了26.09%的用户 (第一版迭代)
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        Map<Character, Integer> diffMap = new HashMap<>(); //多余的字符为正 不足的字符为负数
        for (Character ch : t.toCharArray()) {
            int count = diffMap.getOrDefault(ch, 0);
            diffMap.put(ch, count - 1);
        }

        int left = 0;
        int len = s.length();
        int tLen = t.length();
        int targetL = 0,targetR = Integer.MAX_VALUE;
        for (int right = 0; right < len; right++) {
            char ch = s.charAt(right);
            Integer count = diffMap.get(ch);
            if (count != null) {
                diffMap.put(ch, count + 1);
                if (count + 1 >= 0 && right - left + 1 >= tLen) {
                    boolean noCheck = false;
                    while (left<=right && (noCheck || check(diffMap))) {
                        noCheck = false;
                        if (targetR - targetL > right - left) {
                            targetR = right;
                            targetL = left;
                        }

                        ch = s.charAt(left);
                        count = diffMap.get(ch);
                        if (count == null) {
                            noCheck = true;
                        } else {
                            diffMap.put(ch, count - 1);
                        }
                        left++;
                    }
                }
            }
        }
        if (targetR!=Integer.MAX_VALUE) return s.substring(targetL,targetR+1);
        else return "";
    }

    private boolean check(Map<Character,Integer> diffMap){
        for (Map.Entry<Character, Integer> iter : diffMap.entrySet()) {
            if (iter.getValue() < 0) return false;
        }
        return true;
    }
    public static void main(String args[]){
        no76_minimum_window_substring obj = new no76_minimum_window_substring();
        String target = obj.minWindow("ADOBECODEBANC","ABC");
        System.out.println(target);

        target = obj.minWindow("cabwefgewcwaefgcf", "cae");
        System.out.println(target);

        target = obj.minWindow("ab", "a");
        System.out.println(target);

        target = obj.minWindow("a", "a");
        System.out.println(target);
    }
}
