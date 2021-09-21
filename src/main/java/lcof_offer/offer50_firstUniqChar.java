package lcof_offer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xurongfei
 * @Date 2021/9/21
 * <p>
 * 剑指 Offer 50. 第一个只出现一次的字符
 * <p>
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * <p>
 * 示例 1:
 * 输入：s = "abaccdeff"
 * 输出：'b'
 * <p>
 * 示例 2:
 * 输入：s = ""
 * 输出：' '
 *  
 * 限制：
 * 0 <= s 的长度 <= 50000
 * <p>
 * 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 */
public class offer50_firstUniqChar {
    /**
     * 执行用时：25 ms, 在所有 Java 提交中击败了46.87%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了25.98%的用户
     */
    public char firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> countMap = new LinkedHashMap<>();
        char[] array = s.toCharArray();
        for (char ch : array) {
            Integer count = countMap.getOrDefault(ch, 0);
            countMap.put(ch, count + 1);
        }
        for (Map.Entry<Character, Integer> iter : countMap.entrySet()) {
            if (iter.getValue() == 1) {
                return iter.getKey();
            }
        }

        return ' ';
    }
}
