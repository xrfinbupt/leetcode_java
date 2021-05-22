package Array;


/**
 * 14. 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 提示：
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 *
 */
public class no14_longest_common_prefix {
    public String longestCommonPrefix(String[] strs) {
        int pos = 0;
        while (true) {
            char ch = 0;
            for (String str : strs) {
                if(pos >= str.length()) return str;

                if (ch == 0) ch = str.charAt(pos);
                else {
                    if (str.charAt(pos) != ch) {
                        return pos == 0 ? "" : strs[0].substring(0, pos);
                    }
                }
            }
            pos++;
        }
    }
}
