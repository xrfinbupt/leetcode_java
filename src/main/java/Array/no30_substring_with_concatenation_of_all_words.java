package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 30. 串联所有单词的子串
 *
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 *
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 *
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *  
 * 提示：
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 *
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 *
 */
public class no30_substring_with_concatenation_of_all_words {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        HashSet<Character> set = new HashSet<>();

        HashMap<String,Integer> map = new HashMap<>();
        int wLen = words[0].length();
        int size = words.length;
        int allSize = size * wLen;

        for(String word:words){
            Integer count = map.getOrDefault(word,0);
            map.put(word,count+1);
            for(char ch:word.toCharArray()) set.add(ch);
        }

        int len = s.length();
        for (int i = 0; i <= len - allSize; i++) {
            char ch = s.charAt(i);
            if (set.contains(ch)) {
                int j = 0;
                int ii = i;

                HashMap<String,Integer> map2 = new HashMap<>(map);
                while (j < size) {
                    String str = s.substring(ii, ii + wLen);
                    Integer count = map2.get(str);
                    if (count == null || count < 1) break;
                    else {
                        map2.put(str, count - 1);
                    }
                    j++;
                    ii += wLen;
                }
                if (j == size) {
                    // reset
                    boolean flag = true;
                    for (String key : map2.keySet()) {
                        if(map2.get(key)!=0){
                            flag = false;
                            break;
                        }
                    }
                    if(flag) result.add(i);
                }
            }
        }

        return result;
    }

    public static void main(String args[]) {
        no30_substring_with_concatenation_of_all_words obj = new no30_substring_with_concatenation_of_all_words();
        List<Integer> result = obj.findSubstring("barfoothefoobarman",new String[]{"foo","bar"});
        System.out.println(result);

        result = obj.findSubstring("barfoofoobarthefoobarman",new String[]{"bar","foo","the"});
        System.out.println(result);

        result = obj.findSubstring("wordgoodgoodgoodbestword",new String[]{"word","good","best","good"});
        System.out.println(result);
    }
}
