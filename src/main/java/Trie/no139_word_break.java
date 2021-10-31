package Trie;

import common.TrieNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * <p>
 * Constraints:
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-break
 */
public class no139_word_break {
    boolean failPos[] = new boolean[300];

    /**
     * 超时了
     */
    public boolean wordBreak_org(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        if (wordDict == null || wordDict.size() == 0) return false;

        boolean flag = false;
        for (String iter : wordDict) {
            if (s.startsWith(iter)) {
                String tempS = s.replace(iter, "");
                if (tempS.length() == 0) {
                    return true;
                }
                tempS = s.replaceFirst(iter, "");
                if (tempS.length() == 0) {
                    return true;
                }
                flag = wordBreak(tempS, wordDict);
            }
            if (flag) break;
        }
        return flag;
    }

    /**
     * 官方解答
     * 执行用时：11 ms, 在所有 Java 提交中击败了10.29%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了5.19%的用户
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean dp[] = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> set = new HashSet<String>(wordDict);
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    /**
     * 参考解答：https://leetcode-cn.com/problems/word-break/solution/geekplayes-leetcode-ac-qing-xi-yi-dong-d-vwbu/
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.67%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了68.27%的用户
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            char firstCh = word.charAt(0);
            TrieNode p = root.next[firstCh - 'a'];
            if (p == null) {
                p = new TrieNode();
                root.next[firstCh - 'a'] = p;
            }
            for (int i = 1; i < word.length(); i++) {
                char ch = word.charAt(i);
                TrieNode tempNode = p.next[ch - 'a'];
                if (tempNode == null) {
                    tempNode = new TrieNode();
                    p.next[ch - 'a'] = tempNode;
                }
                p = tempNode;
            }
            p.isEnd = true;
        }

        return dfs(s, root, 0);
    }

    private boolean dfs(String s, TrieNode root, int pos) {
        if (failPos[pos]) {
            //System.out.println("failPos return false:"+pos+" s:"+s);
            return false;
        }
        if (pos >= s.length()) {
            return true;
        }
        TrieNode p = root;
        for (int i = pos; i < s.length(); i++) {
            char ch = s.charAt(i);
            p = p.next[ch - 'a'];
            if (p == null) {
                break;
            }
            if (p.isEnd && dfs(s, root, i + 1)) {
                return true;
            }
        }
        failPos[pos] = true;
        return false;
    }

    public static void main(String[] args) {
        no139_word_break obj = new no139_word_break();
        boolean flag = obj.wordBreak("leetcode", Arrays.asList("leet", "code"));
        System.out.println(flag);

        flag = obj.wordBreak("applepenapple", Arrays.asList("apple", "pen"));
        System.out.println(flag);

        flag = obj.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println(flag);

        flag = obj.wordBreak("applepenapple", Arrays.asList("apple", "pple", "pena"));
        System.out.println(flag);

        flag = obj.wordBreak("cars", Arrays.asList("car", "ca", "rs"));
        System.out.println(flag);

        String input = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        System.out.println(input.length());
        flag = obj.wordBreak(input, Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"));
        System.out.println(flag);
    }
}
