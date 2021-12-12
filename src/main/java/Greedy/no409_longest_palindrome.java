package Greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * 409. 最长回文串
 *
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 * 链接：https://leetcode-cn.com/problems/longest-palindrome
 *
 * @author xurongfei
 * @Date 2021/12/12
 */
public class no409_longest_palindrome {
    /**
     * 执行用时：7 ms, 在所有 Java 提交中击败了31.04%的用户
     * 内存消耗：36.9 MB, 在所有 Java 提交中击败了21.06%的用户
     */
    public int longestPalindrome(String s) {
        int result = 0;
        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            charCountMap.put(ch, charCountMap.getOrDefault(ch, 0) + 1);
        }
        int oddNum = 0;
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            result += entry.getValue();

            if (entry.getValue() % 2 == 1) {
                result--;
                oddNum++;
            }
        }

        if (oddNum > 0) return result + 1;
        return result;
    }
}
