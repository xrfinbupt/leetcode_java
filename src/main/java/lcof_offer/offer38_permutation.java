package lcof_offer;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @author xurongfei
 * @Date 2021/9/11
 * <p>
 * 剑指 Offer 38. 字符串的排列
 * <p>
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 * 限制：
 * 1 <= s 的长度 <= 8
 * <p>
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 */
public class offer38_permutation {
    List<String> result = new ArrayList<>();

    /**
     * 执行用时：9 ms, 在所有 Java 提交中击败了84.58%的用户
     * 内存消耗：42.8 MB, 在所有 Java 提交中击败了61.65%的用户
     */
    public String[] permutation(String s) {
        result = new ArrayList<>();

        char[] array = s.toCharArray();

        dfs(array, 0);
        return result.toArray(new String[]{});
    }

    void dfs(char[] array, int level) {
        if (level >= array.length) {
            result.add(new String(array));
        }
        Set<Character> existSet = new HashSet<>();
        for (int i = level; i < array.length; i++) {
            if(existSet.contains(array[i])) continue;
            existSet.add(array[i]);

            if (i == level) {
                dfs(array, level + 1);
            } else {
                if(i>0 && array[i]==array[i-1]) continue;

                swap(array, level, i);
                dfs(array, level + 1);
                swap(array, level, i);
            }
        }
    }

    void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void main(String[] args){
        offer38_permutation obj = new offer38_permutation();
        String[] res = obj.permutation("abc");
        System.out.println(JSON.toJSONString(res));
        res = obj.permutation("aab");
        System.out.println(JSON.toJSONString(res));
        res = obj.permutation("abca");
        System.out.println(JSON.toJSONString(res));
    }
}
