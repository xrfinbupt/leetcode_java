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
    public String[] permutation2(String s) {
        result = new ArrayList<>();

        char[] array = s.toCharArray();
        dfs(array, 0);
        return result.toArray(new String[]{});
    }

    /**
     * 执行用时：5 ms, 在所有 Java 提交中击败了99.23%的用户
     * 内存消耗：42.7 MB, 在所有 Java 提交中击败了78.18%的用户
     */
    public String[] permutation(String s) {
        result = new ArrayList<>();

        char[] orgArray = s.toCharArray();
        char[] array = new char[orgArray.length];
        boolean[] existFlag = new boolean[orgArray.length];

        Arrays.sort(orgArray);
        dfs2(orgArray, array, existFlag, 0);
        return result.toArray(new String[]{});
    }

    void dfs(char[] array, int level) {
        if (level >= array.length) {
            result.add(new String(array));
        }
        Set<Character> existSet = new HashSet<>();
        for (int i = level; i < array.length; i++) {
            if (existSet.contains(array[i])) continue;
            existSet.add(array[i]);

            if (i == level) {
                dfs(array, level + 1);
            } else {
                if (i > 0 && array[i] == array[i - 1]) continue;

                swap(array, level, i);
                dfs(array, level + 1);
                swap(array, level, i);
            }
        }
    }

    void dfs2(char[] orgArray, char[] array, boolean[] existFlag, int level) {
        if (level >= array.length) {
            result.add(new String(array));
        }
        for (int i = 0; i < array.length; i++) {
            if (existFlag[i] || (i > 0 && !existFlag[i - 1] && orgArray[i] == orgArray[i - 1])) continue;

            existFlag[i] = true;
            array[level] = orgArray[i];
            dfs2(orgArray, array, existFlag, level + 1);
            existFlag[i] = false;
        }
    }

    void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    boolean checkResult(String[] input) {
        if (input == null || input.length < 1) return true;
        Set<String> existSet = new HashSet<>();
        for (String iter : input) {
            if (existSet.contains(iter)) {
                System.out.println(iter);
                return false;
            }
            existSet.add(iter);
        }

        return true;
    }

    public static void main(String[] args) {
        offer38_permutation obj = new offer38_permutation();
        String input = "abc";
        String[] res = obj.permutation(input);
        System.out.println(input + " = " + obj.checkResult(res));

        input = "aab";
        res = obj.permutation(input);
        System.out.println(input + " = " + obj.checkResult(res));

        input = "abca";
        res = obj.permutation(input);
        System.out.println(input + " = " + obj.checkResult(res));

        input = "abac";
        res = obj.permutation(input);
        System.out.println(input + " = " + obj.checkResult(res));

        input = "vpvptjzh";
        res = obj.permutation(input);
        System.out.println(input + " = " + obj.checkResult(res));

        input = "vpvptz";
        res = obj.permutation(input);
        System.out.println(input + " = " + obj.checkResult(res));

        input = "vvpptz";
        res = obj.permutation(input);
        System.out.println(input + " = " + obj.checkResult(res));
        //System.out.println(input + " = " + obj.checkResult(res)+JSON.toJSONString(res));
    }
}
