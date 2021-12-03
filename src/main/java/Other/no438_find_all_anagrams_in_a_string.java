package Other;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * <p>
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * <p>
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *  
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 *
 * @author xurongfei
 * @Date 2021/12/3
 */
public class no438_find_all_anagrams_in_a_string {
    /**
     * 执行用时：3 ms, 在所有 Java 提交中击败了99.97%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了32.01%的用户
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) return res;

        int[] sArray = new int[26];
        int[] pArray = new int[26];
        int pLen = p.length();
        for (int i = 0; i < pLen; i++) {
            int key = s.charAt(i) - 'a';
            int count = sArray[key];
            sArray[key] = count + 1;
        }
        for (char ch : p.toCharArray()) {
            int key = ch - 'a';
            int count = pArray[key];
            pArray[key] = count + 1;
        }

        int len = s.length() - p.length() + 1;
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                char pre = s.charAt(i - 1);
                char curr = s.charAt(i - 1 + pLen);
                sArray[pre - 'a'] = sArray[pre - 'a'] - 1;
                sArray[curr - 'a'] = sArray[curr - 'a'] + 1;
            }
            boolean flag = true;
            for (int j = 0; j < 26; j++) {
                int count1 = sArray[j];
                int count2 = pArray[j];
                if (count1 != count2) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        no438_find_all_anagrams_in_a_string obj = new no438_find_all_anagrams_in_a_string();
        List<Integer> res = obj.findAnagrams("cbaebabacd", "abc");
        System.out.println(JSON.toJSONString(res));
    }
}
