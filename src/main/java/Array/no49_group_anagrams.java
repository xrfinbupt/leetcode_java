package Array;

import java.util.*;

/**
 * 49. 字母异位词分组
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 链接：https://leetcode-cn.com/problems/group-anagrams
 */
public class no49_group_anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null || strs.length == 0) return result;

        Map<String, List<String>> map = new HashMap<>();
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            char array[] = str.toCharArray();
            Arrays.sort(array);

            String key = new String(array);
            List<String> data = map.getOrDefault(key, new ArrayList<>());
            data.add(str);
            map.put(key, data);
        }

        result.addAll(map.values());
        return result;
    }
}
