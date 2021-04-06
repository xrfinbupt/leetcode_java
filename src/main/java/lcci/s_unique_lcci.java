package lcci;

import java.util.HashMap;
import java.util.HashSet;

/**
 *  面试题 01.01. 判定字符是否唯一
 *  实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 * 输入: s = "leetcode"
 * 输出: false
 *
 * 示例 2：
 * 输入: s = "abc"
 * 输出: true
 *
 * 限制：
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 *
 * 链接：https://leetcode-cn.com/problems/is-unique-lcci
 */
public class s_unique_lcci {
    /**
     * 题目中有没有交代字符串的字符一定是26个英文字母，所以最好不好用array[26]来做
     * 面试者不要急于解答 多问清楚约束条件
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {
        if (astr == null || astr.isEmpty()) return true;
        HashSet<Character> map = new HashSet<>();
        for (char ch : astr.toCharArray()) {
            if (map.contains(ch)) {
                return false;
            }
            map.add(ch);
        }
        return true;
    }
    public static void main(String args[]){
        int result = 5 ^6^ 7^5;
        System.out.println(result);
        result = 5^7 ^ 6;
        System.out.println(result);
        System.out.println();
    }
}
