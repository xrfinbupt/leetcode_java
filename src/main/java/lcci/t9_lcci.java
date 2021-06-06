package lcci;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 面试题 16.20. T9键盘
 *
 * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。
 * 给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：
 *
 * 示例 1:
 * 输入: num = "8733", words = ["tree", "used"]
 * 输出: ["tree", "used"]
 *
 * 示例 2:
 * 输入: num = "2", words = ["a", "b", "c", "d"]
 * 输出: ["a", "b", "c"]
 *
 * 提示：
 * num.length <= 1000
 * words.length <= 500
 * words[i].length == num.length
 * num中不会出现 0, 1 这两个数字
 *
 * 链接：https://leetcode-cn.com/problems/t9-lcci
 */
public class t9_lcci {
    /**
     * 执行用时：16 ms, 在所有 Java 提交中击败了28.83%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了93.47%的用户
     *
     * @param num
     * @param words
     * @return
     */
    public List<String> getValidT9Words(String num, String[] words) {
        if (num == null || num.length() == 0) return new ArrayList<>();

        // 2 - 9
        String nums[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();

        Set<String> skipWordSet = new HashSet<>();
        int len = num.length();
        int wordLen = words.length;
        for (int i = 0; i < len; i++) {
            int currNum = num.charAt(i) - '0';
            String targetChars = nums[currNum - 2];
            for (String word : words) {
                if (skipWordSet.contains(word)) continue;

                boolean flag = false;
                for (char chIter : targetChars.toCharArray()) {
                    if (chIter == word.charAt(i)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) skipWordSet.add(word);
            }
            if (skipWordSet.size() == wordLen) return new ArrayList<>();
        }

        for (String word : words) {
            if (skipWordSet.contains(word)) continue;
            result.add(word);
        }
        return result;
    }
}
